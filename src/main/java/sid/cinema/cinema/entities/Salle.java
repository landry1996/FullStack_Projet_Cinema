package sid.cinema.cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int numberPlaces;
    @ManyToOne
    private Cinema cinema;
    @OneToMany(mappedBy = "salle")
    private Collection<Place> places;
    @OneToMany(mappedBy = "salle")
    private Collection<ProjectionFilm> projection;

}
