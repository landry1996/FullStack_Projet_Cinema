package sid.cinema.cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude,latitude,altitude;
    private int numberSalle;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
    @ManyToOne
    private Ville ville;

}
