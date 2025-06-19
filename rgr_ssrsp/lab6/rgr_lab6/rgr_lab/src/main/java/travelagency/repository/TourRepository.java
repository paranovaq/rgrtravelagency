package travelagency.repository;

import travelagency.entity.Tour;
import travelagency.entity.TourGuide;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @EntityGraph(attributePaths = "tourGuides")
    List<Tour> findAll();

    @EntityGraph(attributePaths = "tourGuides")
    Optional<Tour> findById(Long id);

    @EntityGraph(attributePaths = "tourGuides")
    List<Tour> findByNameContainingIgnoreCase(String name);

    Tour findByTourCode(String tourCode);

    @EntityGraph(attributePaths = "tourGuides")
    List<Tour> findByStatus(String status);

    @EntityGraph(attributePaths = "tourGuides")
    List<Tour> findByType(String type);

    @Query("SELECT tg FROM TourGuide tg JOIN tg.tours t WHERE t.id = :tourId")
    List<TourGuide> findTourGuidesByTourId(@Param("tourId") Long tourId);

    @EntityGraph(attributePaths = "tourGuides")
    Optional<Tour> findWithTourGuidesById(Long id);

    List<Tour> findByDestinationContainingIgnoreCase(String destination);
}