package com.example.rasha;

import com.example.rasha.model.DTO.MovieDTO;
import com.example.rasha.model.Movie;
import com.example.rasha.repository.MovieRepository;
import com.example.rasha.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testImportCSV() throws Exception {
        String csvData = "1990,Action,Some Movie,Additional Info,Yes\n" +
                "1991,Drama,Another Movie,Info,No";
        InputStream is = new ByteArrayInputStream(csvData.getBytes());
        MultipartFile multipartFile = new MockMultipartFile("file", is);

        movieService.importCSV(multipartFile);

        ArgumentCaptor<List<Movie>> argument = ArgumentCaptor.forClass(List.class);
        verify(movieRepository).saveAll(argument.capture());
        List<Movie> capturedMovies = argument.getValue();

        Assertions.assertEquals(2, capturedMovies.size());
        Assertions.assertEquals("Some Movie", capturedMovies.get(0).getNominee());
    }

    @Test
    void testConvertToMovieEntity() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setYear("2020");
        movieDTO.setCategory("Drama");
        movieDTO.setNominee("Test Movie");
        movieDTO.setAdditionalInfo("Info");
        movieDTO.setWon("Yes");

        Movie movie = movieService.convertToMovieEntity(movieDTO);

        Assertions.assertNotNull(movie);
        Assertions.assertEquals("2020", movie.getYear());
        Assertions.assertEquals("Drama", movie.getCategory());
        Assertions.assertEquals("Test Movie", movie.getNominee());
        Assertions.assertEquals("Info", movie.getAdditionalInfo());
        Assertions.assertEquals("Yes", movie.getWon());
    }

    @Test
    void testFilterByWon() {
        Movie movie1 = new Movie();
        movie1.setWon("Yes");
        Movie movie2 = new Movie();
        movie2.setWon("No");

        when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));

        List<Movie> filteredMovies = movieService.filterByWon();

        Assertions.assertEquals(1, filteredMovies.size());
        Assertions.assertTrue(filteredMovies.contains(movie1));
    }
}
