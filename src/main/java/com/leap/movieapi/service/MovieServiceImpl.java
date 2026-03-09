package com.leap.movieapi.service;


import com.leap.movieapi.entity.Movie;
import com.leap.movieapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository repository;
    public MovieServiceImpl(MovieRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Movie> getAllMovies(){
        return repository.findAll();
    }
    @Override
    public Movie getMovieById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
    @Override
    public Movie createMovie(Movie movie){
        return repository.save(movie);
    }
    @Override
    public Movie updateMovie(Long id, Movie updatedMovie){
        Movie existingMovie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setYear(updatedMovie.getYear());
        existingMovie.setRating(updatedMovie.getRating());

        return repository.save(existingMovie);
    }
    @Override
    public void deleteMovie(Long id){
        Movie existingMovie = repository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        repository.delete(existingMovie);
    }

}
