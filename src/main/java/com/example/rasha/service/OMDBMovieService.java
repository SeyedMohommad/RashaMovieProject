package com.example.rasha.service;

import com.example.rasha.model.DTO.OMDBMovieDTO;
import com.example.rasha.model.OMDBMovie;
import com.example.rasha.repository.OMDBMovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OMDBMovieService {

    private final ObjectMapper objectMapper;
    @Value("${omdb.api.key}")
    private String apiKey;

    @Autowired
    private OMDBMovieRepository omdbMovieRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "http://www.omdbapi.com/";

    public OMDBMovieService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }




    public String getMovieDetailsByTitle(String title) {
        String url = apiUrl + "?t=" + title + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
    public String getMovieDetailsBySearch(String search,int page) {
        String url = apiUrl + "?s=" + search + "&apikey=" + apiKey+"&page="+page;
        return restTemplate.getForObject(url, String.class);
    }
    public String getMovieDetailsByIMDBId(String imdbId) {
        String url = apiUrl + "?i=" + imdbId + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
    @Transactional
    public OMDBMovie rateToTheMovie(String imdbId, String value,String source){
        OMDBMovieDTO omdbMovieDTO;
        String omdbMovieStr = getMovieDetailsByIMDBId(imdbId);
        try {
            omdbMovieDTO = objectMapper.readValue(omdbMovieStr,OMDBMovieDTO.class);
            OMDBMovie.Rating rating1 = new OMDBMovie.Rating();
            rating1.setValue(value);
            rating1.setSource(source);
            List<OMDBMovie.Rating> ratings;
            OMDBMovie omdbMovie = convertDtoToEntity(omdbMovieDTO);
            ratings = omdbMovie.getRatings();
            ratings.add(rating1);
            omdbMovie.setRatings(ratings);
            saveOMDBMovie(omdbMovie);
            return omdbMovie;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public OMDBMovie convertDtoToEntity(OMDBMovieDTO dto) {
        OMDBMovie movie = new OMDBMovie();
        movie.setTitle(dto.getTitle());
        movie.setYear(dto.getYear());
        movie.setRated(dto.getRated());
        movie.setReleased(dto.getReleased());
        movie.setRuntime(dto.getRuntime());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setWriter(dto.getWriter());
        movie.setActors(dto.getActors());
        movie.setPlot(dto.getPlot());
        movie.setLanguage(dto.getLanguage());
        movie.setCountry(dto.getCountry());
        movie.setAwards(dto.getAwards());
        movie.setPoster(dto.getPoster());

        // Copying the ratings from DTO to Entity
        if (dto.getRatings() != null) {
            List<OMDBMovie.Rating> ratings = new ArrayList<>();
            for (OMDBMovieDTO.Rating dtoRating : dto.getRatings()) {
                OMDBMovie.Rating entityRating = new OMDBMovie.Rating();
                entityRating.setSource(dtoRating.getSource());
                entityRating.setValue(dtoRating.getValue());
                ratings.add(entityRating);
            }
            movie.setRatings(ratings);
        }

        movie.setMetascore(dto.getMetascore());
        movie.setImdbRating(dto.getImdbRating());
        movie.setImdbVotes(dto.getImdbVotes());
        movie.setImdbID(dto.getImdbID());
        movie.setType(dto.getType());
        movie.setResponse(dto.getResponse());

        return movie;
    }


    public void saveOMDBMovie(OMDBMovie movie) {

        omdbMovieRepository.save(movie);
    }
}
