package com.leap.movieapi.controller;


import com.leap.movieapi.entity.Movie;
import com.leap.movieapi.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return service.getAllMovies();
    }
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id){
        return service.getMovieById(id);
    }
    @PostMapping
    public Movie createMovie(@Valid @RequestBody Movie movie){
        return  service.createMovie(movie);
    }
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @Valid @RequestBody Movie move){
        return  service.updateMovie(id, move);
    }
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        service.deleteMovie(id);
    }
}
