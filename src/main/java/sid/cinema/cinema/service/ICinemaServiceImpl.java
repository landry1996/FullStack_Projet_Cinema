package sid.cinema.cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sid.cinema.cinema.dao.*;
import sid.cinema.cinema.entities.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
public class ICinemaServiceImpl implements ICinemaService {
    private VilleRepository villeRepository;
    private CinemaRepository cinemaRepository;
    private SalleRepository salleRepository;
    private PlaceRepository placeRepository;
    private FilmRepository filmRepository;
    private CategorieRepository categorieRepository;
    private SeanceRepository seanceRepository;
    private ProjectionFilmRepository projectionFilmRepository;
    private TicketRepository ticketRepository;

    @Override
    public void initializedVille() {
        Stream.of("Douala","Yaoundé","Loum","Kribi","Bertoua")
                .forEach(v->{
                    var vi = new Ville();
                    vi.setName(v);
                   /* vi.setLongitude(7.5);
                    vi.setLatitude(3.1);
                    vi.setLatitude(4.4);*/
                    villeRepository.save(vi);

                });
    }

    @Override
    public void initializedCinema() {
       villeRepository.findAll().forEach(ville -> {
           Stream.of("EDEN","WOURI","MovieCiné","IMaxCine","4KCine")
                   .forEach(ci->{
                       var cinema = new Cinema();
                       cinema.setName(ci);
                       cinema.setVille(ville);
                     /*  cinema.setLongitude();
                       cinema.setLatitude();
                       cinema.setAltitude();*/
                       cinema.setNumberSalle( 4+(int)(Math.random()*7));
                       cinemaRepository.save(cinema);

                      });
                   });
    }

    @Override
    public void initializedSalle() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0;i<cinema.getNumberSalle();i++){
                var salle = new Salle();
                salle.setName("salle"+(i+1));
                salle.setCinema(cinema);
                salle.setNumberPlaces(15+(int)(Math.random()*20));
                salleRepository.save(salle);

            }
        });

    }

    @Override
    public void initializedPlace() {
     salleRepository.findAll().forEach(salle -> {
         for (int i = 0; i<salle.getNumberPlaces(); i++){
             var place = new Place();
             place.setNumber(i+1);
             place.setSalle(salle);
             placeRepository.save(place);
         }
     });

    }

    @Override
    public void initializedSeance() {
        // spécifiée l'heure en chaine de caractere
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
     Stream.of("10:00","12:00", "15:00","17:00","19:00")
             .forEach(se -> {
                 var s = new Seance();
                 try {
                     s.setStartTime(dateFormat.parse(se));
                     seanceRepository.save(s);
                 } catch (ParseException e) {
                     throw new RuntimeException(e);
                 }
             });

    }

   @Override
    public void initializedCategorie() {
          Stream.of("Story","Action","Fiction","Drama")
                  .forEach(cat->{
                      var ca = new Categorie();
                      ca.setName(cat);
                      categorieRepository.save(ca);
                  });
    }

    @Override
    public void initializedFilms() {
        double[] duree = new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories = categorieRepository.findAll();
     Stream.of("BUMBLEBEE","THE LOST CITY","FAST X","VENOM","TARZAN","MORBIUS","ANGEL HAS FALLEN",
             "AVENGER Infinity War ","THOR Love and Thunder","ZACK SNYDERS(justice league)","MISSION IMPOSSIBLE","UNCHARTED")
             .forEach(film->{
                 var fi = new Film();
                 fi.setTitle(film);
                 fi.setDuration(duree[new Random().nextInt(duree.length)]);
                 fi.setPhoto(film.replaceAll(" ",""));
                 fi.setCategorie(categories.get(new Random().nextInt(categories.size())));
                 filmRepository.save(fi);

             });


    }

    @Override
    public void initializedProjection() {
        double[] prix = new double[]{35,55,65,75,95,105};
    villeRepository.findAll().forEach(ville -> {
        ville.getCinemas().forEach(cinema -> {
            cinema.getSalles().forEach(salle -> {
                filmRepository.findAll().forEach(film -> {
                    seanceRepository.findAll().forEach(seance -> {
                        var projection = new ProjectionFilm();
                        projection.setScreeningDate(new Date());
                        projection.setFilm(film);
                        projection.setPrice(prix[new Random().nextInt(prix.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionFilmRepository.save(projection);

                    });
                });
            });
        });


    });


    }

    @Override
    public void initializedTicket() {
        projectionFilmRepository.findAll().forEach(pro->{
            pro.getSalle().getPlaces().forEach(place -> {
                var ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrice(pro.getPrice());
                ticket.setProjectionFilm(pro);
                ticket.setReserved(false);
                ticketRepository.save(ticket);
            });
        });

    }
}
