package travelagency.repository;

import travelagency.entity.TourGuide;
import travelagency.entity.Tour;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface TourGuideRepository extends JpaRepository<TourGuide, Long> {
    @EntityGraph(attributePaths = "tours")
    List<TourGuide> findAll();

    @EntityGraph(attributePaths = "tours")
    List<TourGuide> findBySpecialization(String specialization);

    @EntityGraph(attributePaths = "tours")
    List<TourGuide> findByLanguagesContaining(String language);

    @EntityGraph(attributePaths = "tours")
    List<TourGuide> findByFirstNameContainingOrLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("SELECT t FROM Tour t JOIN t.tourGuides tg WHERE tg.id = :tourGuideId")
    List<Tour> findToursByTourGuideId(@Param("tourGuideId") Long tourGuideId);

    @Query("SELECT COUNT(t) FROM Tour t JOIN t.tourGuides tg WHERE tg.id = :tourGuideId")
    Long countToursByTourGuideId(@Param("tourGuideId") Long tourGuideId);

    @EntityGraph(attributePaths = "tours")
    Optional<TourGuide> findWithToursById(Long id);
}