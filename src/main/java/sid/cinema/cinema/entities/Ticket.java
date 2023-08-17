package sid.cinema.cinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCustomer;
    private double price;
    @Column(unique = true)
    private int paymentCode;
    private boolean reserved;
    @ManyToOne
    private Place place;
   @ManyToOne
    private ProjectionFilm projectionFilm;
}
