package travelagency.service;

import travelagency.entity.TourGuide;
import travelagency.entity.Tour;
import java.util.List;
import java.util.Optional;

public interface TourGuideService extends Service<TourGuide> {
    List<TourGuide> findBySpecialization(String specialization);
    List<TourGuide> findByLanguage(String language);
    Optional<TourGuide> findByIdWithDetails(Long id);
    List<TourGuide> findByNameContainingIgnoreCase(String name);
    List<Tour> findToursByTourGuideId(Long tourGuideId);
    Long countToursByTourGuideId(Long tourGuideId);
    void assignTourToTourGuide(Long tourGuideId, Long tourId);
    void removeTourFromTourGuide(Long tourGuideId, Long tourId);
    TourGuide updateTourGuide(Long id, TourGuide tourGuideDetails);
}