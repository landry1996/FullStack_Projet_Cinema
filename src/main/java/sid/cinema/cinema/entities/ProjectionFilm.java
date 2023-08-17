package sid.cinema.cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ProjectionFilm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date screeningDate;
    private double price;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Film film;
    @OneToMany(mappedBy = "projectionFilm")
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;

}
