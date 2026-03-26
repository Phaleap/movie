package com.leap.movieapi.service;

import com.leap.movieapi.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie createMovie(Movie movie);
    Movie updateMovie(Long id, String title, String genre, Integer year, Double rating, MultipartFile image) throws IOException;
    void deleteMovie(Long id);
}