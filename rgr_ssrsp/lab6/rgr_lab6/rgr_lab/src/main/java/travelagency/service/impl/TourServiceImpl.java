package travelagency.service.impl;

import travelagency.entity.Tour;
import travelagency.repository.TourRepository;
import travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Page<Tour> findAll(Pageable pageable) {
        return tourRepository.findAll(pageable);
    }

    @Override
    public Tour save(Tour entity) {
        return tourRepository.save(entity);
    }

    @Override
    public Tour update(Tour entity) {
        return tourRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tourRepository.existsById(id);
    }

    @Override
    public List<Tour> findByNameContaining(String name) {
        return tourRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Tour findByTourCode(String tourCode) {
        return tourRepository.findByTourCode(tourCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tour> findByIdWithDetails(Long id) {
        return tourRepository.findWithTourGuidesById(id);
    }

    @Override
    public List<Tour> findByStatus(String status) {
        return tourRepository.findByStatus(status);
    }

    @Override
    public List<Tour> findByType(String type) {
        return tourRepository.findByType(type);
    }

    @Override
    public List<Tour> findByDestinationContaining(String destination) {
        return tourRepository.findByDestinationContainingIgnoreCase(destination);
    }
}