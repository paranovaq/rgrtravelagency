package travelagency.controller;

import travelagency.entity.Tour;
import travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/tours")
public class TourController extends AbstractController<Tour> {

    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public TourService getService() {
        return tourService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Tour>> getToursByName(@PathVariable String name) {
        List<Tour> tours = tourService.findByNameContaining(name);
        return tours.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tours, headers, HttpStatus.OK);
    }

    @GetMapping("/code/{tourCode}")
    public ResponseEntity<Tour> getTourByCode(@PathVariable String tourCode) {
        Tour tour = tourService.findByTourCode(tourCode);
        return tour != null
                ? new ResponseEntity<>(tour, headers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Tour>> getToursByStatus(@PathVariable String status) {
        List<Tour> tours = tourService.findByStatus(status);
        return tours.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tours, headers, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Tour>> getToursByType(@PathVariable String type) {
        List<Tour> tours = tourService.findByType(type);
        return tours.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tours, headers, HttpStatus.OK);
    }

    @GetMapping("/destination/{destination}")
    public ResponseEntity<List<Tour>> searchToursByDestination(@PathVariable String destination) {
        List<Tour> tours = tourService.findByDestinationContaining(destination);
        return tours.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(tours, headers, HttpStatus.OK);
    }
}