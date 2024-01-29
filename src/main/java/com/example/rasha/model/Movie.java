package com.example.rasha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 4)
    private String year;

    @Column(length = 255)
    private String director;

    @Column(columnDefinition = "TEXT")
    private String actors;

    @Column(length = 255)
    private String genre;

    @Column(length = 10)
    private String runtime;

    @Column(length = 255)
    private String language;

    @Column(length = 255)
    private String country;

    @Column(columnDefinition = "TEXT")
    private String posterUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;



}
