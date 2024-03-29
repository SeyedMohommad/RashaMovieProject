package com.example.rasha.controller;
import com.example.rasha.model.DTO.OMDBMovieDTO;
import com.example.rasha.model.Movie;
import com.example.rasha.model.OMDBMovie;
import com.example.rasha.service.MovieService;
import com.example.rasha.service.OMDBMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private OMDBMovieService omdbMovieService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        movieService.importCSV(file);
        return "File uploaded successfully!";
    }

    @GetMapping("/winners")
    public List<Movie> winners(){
        return movieService.filterByWon();
    }
    @GetMapping("/SearchByID/{imdbId}")
    public ResponseEntity<String> searchByImdbId(@PathVariable String imdbId) {
        String movieJson = omdbMovieService.getMovieDetailsByIMDBId(imdbId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieJson);
    }
    @GetMapping("/SearchByTitle/{title}")
    public ResponseEntity<String> searchByTitle(@PathVariable String title) {
        String movieJson = omdbMovieService.getMovieDetailsByTitle(title);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieJson);
    }
    @GetMapping("/Search/{search}/{page}")
    public ResponseEntity<String> getMovieDetailsBySearch(@PathVariable String search,@PathVariable int page) {
        String movieJson = omdbMovieService.getMovieDetailsBySearch(search, page);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieJson);
    }

    @GetMapping("/Rating/{imdbId}&{source}&{value}")
    public ResponseEntity<OMDBMovie> ratingToMovie(@PathVariable String imdbId, @PathVariable String source, @PathVariable String value){
        OMDBMovie omdbMovie = omdbMovieService.rateToTheMovie(imdbId,source,value);
        return ResponseEntity.ok(omdbMovie);
    }
}