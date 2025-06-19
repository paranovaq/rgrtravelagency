package travelagency.service.impl;

import travelagency.entity.TourGuide;
import travelagency.entity.Tour;
import travelagency.exception.TravelAgencyResourceNotFoundException;
import travelagency.repository.TourGuideRepository;
import travelagency.repository.TourRepository;
import travelagency.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    private final TourGuideRepository tourGuideRepository;
    private final TourRepository tourRepository;

    @Autowired
    public TourGuideServiceImpl(TourGuideRepository tourGuideRepository,
                                TourRepository tourRepository) {
        this.tourGuideRepository = tourGuideRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public Optional<TourGuide> findById(Long id) {
        return tourGuideRepository.findById(id);
    }

    @Override
    public List<TourGuide> findAll() {
        return tourGuideRepository.findAll();
    }

    @Override
    public Page<TourGuide> findAll(Pageable pageable) {
        return tourGuideRepository.findAll(pageable);
    }

    @Override
    public TourGuide save(TourGuide entity) {
        return tourGuideRepository.save(entity);
    }

    @Override
    public TourGuide update(TourGuide entity) {
        return tourGuideRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        tourGuideRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tourGuideRepository.existsById(id);
    }

    @Override
    public List<TourGuide> findBySpecialization(String specialization) {
        return tourGuideRepository.findBySpecialization(specialization);
    }

    @Override
    public List<TourGuide> findByLanguage(String language) {
        return tourGuideRepository.findByLanguagesContaining(language);
    }

    @Override
    public Optional<TourGuide> findByIdWithDetails(Long id) {
        return tourGuideRepository.findWithToursById(id);
    }

    @Override
    public List<TourGuide> findByNameContainingIgnoreCase(String name) {
        return tourGuideRepository.findByFirstNameContainingOrLastNameContainingIgnoreCase(name, name);
    }

    @Override
    public List<Tour> findToursByTourGuideId(Long tourGuideId) {
        return tourGuideRepository.findToursByTourGuideId(tourGuideId);
    }

    @Override
    public Long countToursByTourGuideId(Long tourGuideId) {
        return tourGuideRepository.countToursByTourGuideId(tourGuideId);
    }

    @Override
    @Transactional
    public void assignTourToTourGuide(Long tourGuideId, Long tourId) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new TravelAgencyResourceNotFoundException("Tour guide not found"));
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new TravelAgencyResourceNotFoundException("Tour not found"));

        tourGuide.getTours().add(tour);
        tour.getTourGuides().add(tourGuide);

        tourGuideRepository.save(tourGuide);
        tourRepository.save(tour);
    }

    @Override
    @Transactional
    public void removeTourFromTourGuide(Long tourGuideId, Long tourId) {
        TourGuide tourGuide = tourGuideRepository.findById(tourGuideId)
                .orElseThrow(() -> new TravelAgencyResourceNotFoundException("Tour guide not found"));
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new TravelAgencyResourceNotFoundException("Tour not found"));

        tourGuide.getTours().remove(tour);
        tour.getTourGuides().remove(tourGuide);
    }

    @Override
    public TourGuide updateTourGuide(Long id, TourGuide tourGuideDetails) {
        TourGuide tourGuide = tourGuideRepository.findById(id)
                .orElseThrow(() -> new TravelAgencyResourceNotFoundException("Tour guide not found"));

        tourGuide.setFirstName(tourGuideDetails.getFirstName());
        tourGuide.setLastName(tourGuideDetails.getLastName());
        tourGuide.setSpecialization(tourGuideDetails.getSpecialization());
        tourGuide.setLanguages(tourGuideDetails.getLanguages());

        return tourGuideRepository.save(tourGuide);
    }
}