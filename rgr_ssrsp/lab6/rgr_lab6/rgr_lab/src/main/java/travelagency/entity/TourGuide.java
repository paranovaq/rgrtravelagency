package travelagency.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tour_guides")
public class TourGuide extends AbstractEntity {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String specialization; // historical, adventure, cultural, etc.

    @Column(nullable = false)
    private String languages; // comma-separated list of languages

    @ManyToMany
    @JoinTable(
            name = "tour_guide_tour",
            joinColumns = @JoinColumn(name = "tour_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    @JsonIgnoreProperties("tourGuides")
    private Set<Tour> tours = new HashSet<>();

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getLanguages() { return languages; }
    public void setLanguages(String languages) { this.languages = languages; }
    public Set<Tour> getTours() { return tours; }
    public void setTours(Set<Tour> tours) { this.tours = tours; }
}