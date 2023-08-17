package sid.cinema.cinema;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sid.cinema.cinema.service.ICinemaService;

@SpringBootApplication
@AllArgsConstructor
public class CinemaApplication implements CommandLineRunner {

    private ICinemaService iCinemaService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     iCinemaService.initializedVille();
     iCinemaService.initializedCinema();
     iCinemaService.initializedSalle();
     iCinemaService.initializedPlace();
     iCinemaService.initializedSeance();
     iCinemaService.initializedCategorie();
     iCinemaService.initializedFilms();
     iCinemaService.initializedProjection();
     iCinemaService.initializedTicket();



    }
}
