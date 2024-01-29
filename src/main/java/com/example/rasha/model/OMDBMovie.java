package com.example.rasha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "omdbmovie")
public class OMDBMovie {

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String type;
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;
    private String response;
    @Id
    @GeneratedValue
    private Long id;


    // Constructors, getters and setters

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "rating")
    public static class Rating {

        private String source;
        private String value;
        @Id
        @GeneratedValue
        private Long id;

        // Constructors, getters and setters
    }
}
