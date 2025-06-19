package travelagency.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
public class Tour extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // group, individual, cruise, etc.

    @Column(name = "tour_code", nullable = false, unique = true)
    private String tourCode;

    @Column(nullable = false)
    private String status; // available, booked, completed

    @Column(nullable = false)
    private String destination;

    @ManyToMany(mappedBy = "tours")
    @JsonIgnoreProperties("tours")
    private Set<TourGuide> tourGuides = new HashSet<>();

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTourCode() { return tourCode; }
    public void setTourCode(String tourCode) { this.tourCode = tourCode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public Set<TourGuide> getTourGuides() { return tourGuides; }
    public void setTourGuides(Set<TourGuide> tourGuides) { this.tourGuides = tourGuides; }
}