//package com.example.rasha;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//class MovieServiceTest {
//
//    @Mock
//    private MovieRepository movieRepository;
//
//    private MovieService movieService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        movieService = new MovieService(movieRepository);
//    }
//
//    @Test
//    void getMovieDetails_validTitle_returnsMovie() {
//        // Arrange
//        String title = "ValidTitle";
//        Movie movie = new Movie(); // create a movie object with expected properties
//        when(movieRepository.findByTitle(title)).thenReturn(movie);
//
//        // Act
//        Movie result = movieService.getMovieDetails(title);
//
//        // Assert
//        assertEquals(movie, result);
//        verify(movieRepository).findByTitle(title);
//    }
//
//    // Additional tests...
//}
