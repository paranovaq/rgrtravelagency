package travelagency.controller;

import travelagency.entity.TourGuide;
import travelagency.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/tour-guides")
public class TourGuideController extends AbstractController<TourGuide> {

    private final TourGuideService tourGuideService;

    @Autowired
    public TourGuideController(TourGuideService tourGuideService) {
        this.tourGuideService = tourGuideService;
    }

    @Override
    public TourGuideService getService() {
        return tourGuideService;
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<TourGuide>> getBySpecialization(@PathVariable String specialization) {
        List<TourGuide> tourGuides = tourGuideService.findBySpecialization(specialization);
        return tourGuides.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tourGuides, headers, HttpStatus.OK);
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<TourGuide>> getByLanguage(@PathVariable String language) {
        List<TourGuide> tourGuides = tourGuideService.findByLanguage(language);
        return tourGuides.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tourGuides, headers, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<TourGuide>> searchTourGuides(@PathVariable String name) {
        List<TourGuide> tourGuides = tourGuideService.findByNameContainingIgnoreCase(name);
        return tourGuides.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tourGuides, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}/tours/count")
    public ResponseEntity<Long> getTourCountForTourGuide(@PathVariable Long id) {
        return new ResponseEntity<>(tourGuideService.countToursByTourGuideId(id), headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/{tourGuideId}/tours/{tourId}")
    public ResponseEntity<Void> assignTourToTourGuide(
            @PathVariable Long tourGuideId,
            @PathVariable Long tourId) {
        tourGuideService.assignTourToTourGuide(tourGuideId, tourId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{tourGuideId}/tours/{tourId}")
    public ResponseEntity<Void> removeTourFromTourGuide(
            @PathVariable Long tourGuideId,
            @PathVariable Long tourId) {
        tourGuideService.removeTourFromTourGuide(tourGuideId, tourId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}