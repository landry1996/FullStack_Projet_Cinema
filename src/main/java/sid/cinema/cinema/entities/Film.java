package sid.cinema.cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double duration;
    private String director;
    private String description;
    private String photo;
    private Date releaseDate;
    @OneToMany(mappedBy = "film")
    private Collection<ProjectionFilm> projection;
    @ManyToOne
    private Categorie categorie;


}
