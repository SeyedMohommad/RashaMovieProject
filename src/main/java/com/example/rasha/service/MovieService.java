package com.example.rasha.service;
import com.example.rasha.model.DTO.MovieDTO;
import com.example.rasha.model.Movie;
import com.example.rasha.repository.MovieRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void importCSV(MultipartFile file) {
        List<Movie> movies = new ArrayList<>();
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                MovieDTO movieDTO = new MovieDTO();

                // Populate the MovieDTO fields from the CSV data
                movieDTO.setYear(values[0]); // Assuming the CSV structure matches the field order
                movieDTO.setCategory(values[1]);
                movieDTO.setNominee(values[2]);
                movieDTO.setAdditionalInfo(values[3]);
                movieDTO.setWon(values[4]);

                // Add the MovieDTO to the list

                Movie movie = convertToMovieEntity(movieDTO);
                movies.add(movie);
            }


            movieRepository.saveAll(movies);

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
    // Method to convert MovieDTO to Movie entity
    private Movie convertToMovieEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setYear(movieDTO.getYear());
        movie.setCategory(movieDTO.getCategory());
        movie.setNominee(movieDTO.getNominee());
        movie.setAdditionalInfo(movieDTO.getAdditionalInfo());
        movie.setWon(movieDTO.getWon());
        // Set any other fields as needed
        return movie;
    }
}

