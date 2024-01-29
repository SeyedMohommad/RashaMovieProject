package com.example.rasha.service;
import com.example.rasha.model.Movie;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OmdbMovieService {

    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://www.omdbapi.com/";

    @Cacheable("movie")
    public Movie getMovieDetails(String title) {
        try {
            String url = apiUrl + "?t=" + title + "&apikey=" + apiKey;
            return restTemplate.getForObject(url, Movie.class);
        } catch (RestClientException e) {
            // Log the error and handle it, maybe throw a custom exception
            return null; // Or handle it differently
        }
    }
}

