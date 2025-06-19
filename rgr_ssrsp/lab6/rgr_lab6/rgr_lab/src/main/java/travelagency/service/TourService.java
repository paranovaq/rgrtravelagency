package travelagency.service;

import travelagency.entity.Tour;
import java.util.List;
import java.util.Optional;

public interface TourService extends Service<Tour> {
    Tour findByTourCode(String tourCode);
    Optional<Tour> findByIdWithDetails(Long id);
    List<Tour> findByNameContaining(String name);
    List<Tour> findByStatus(String status);
    List<Tour> findByType(String type);
    List<Tour> findByDestinationContaining(String destination);

}