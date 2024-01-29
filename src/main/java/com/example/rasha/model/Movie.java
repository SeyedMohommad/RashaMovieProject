package com.example.rasha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    private String year;
    private String category;
    @Column(columnDefinition = "TEXT")
    private String nominee;
    private String additionalInfo;
    private String won;
    @Id
    @GeneratedValue
    private Long id;


}
