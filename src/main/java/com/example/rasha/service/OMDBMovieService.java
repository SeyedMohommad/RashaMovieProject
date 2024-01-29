package com.example.rasha.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OMDBMovieService {
    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://www.omdbapi.com/";

    public String getMovieDetailsByTitle(String title) {
        String url = apiUrl + "?t=" + title + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
    public String getMovieDetailsBySearch(String search) {
        String url = apiUrl + "?s=" + search + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
    public String getMovieDetailsByIMDBId(String imdbId) {
        String url = apiUrl + "?i=" + imdbId + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

}
