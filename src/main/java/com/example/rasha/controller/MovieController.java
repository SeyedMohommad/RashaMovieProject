package com.example.rasha.controller;
import com.example.rasha.model.Movie;
import com.example.rasha.service.OmdbMovieService;
import com.example.rasha.service.OscarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final OmdbMovieService omdbMovieService;
    private final OscarService oscarService; // Service to check Oscar awards

    @Autowired
    public MovieController(OmdbMovieService omdbMovieService, OscarService oscarService) {
        this.omdbMovieService = omdbMovieService;
        this.oscarService = oscarService;
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getMovieDetails(@PathVariable String title) {
        Movie movie = omdbMovieService.getMovieDetails(title);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{title}/oscar")
    public ResponseEntity<?> checkOscar(@PathVariable String title) {
        boolean hasWonOscar = oscarService.checkOscarAward(title);
        if (hasWonOscar) {
            return ResponseEntity.ok(title + " has won an Oscar.");
        } else {
            return ResponseEntity.ok(title + " has not won an Oscar.");
        }
    }
}
