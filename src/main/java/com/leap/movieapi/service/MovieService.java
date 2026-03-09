package com.leap.movieapi.service;

import com.leap.movieapi.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie createMovie(Movie movie);
    Movie updateMovie(Long id, Movie updatedMovie);
    void deleteMovie(Long id);
}
