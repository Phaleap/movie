package com.leap.movieapi.service;

import com.leap.movieapi.entity.Movie;
import com.leap.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public Movie createMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, String title, String genre, Integer year, Double rating, MultipartFile image) throws IOException {
        Movie existingMovie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingMovie.setTitle(title);
        existingMovie.setGenre(genre);
        existingMovie.setYear(year);
        existingMovie.setRating(rating);

        if (image != null && !image.isEmpty()) {
            String originalFilename = StringUtils.cleanPath(image.getOriginalFilename());
            String extension = "";

            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex >= 0) {
                extension = originalFilename.substring(dotIndex);
            }

            String newFileName = UUID.randomUUID() + extension;

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(image.getInputStream(), uploadPath.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

            existingMovie.setImageName(newFileName);
        }

        return repository.save(existingMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie existingMovie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        repository.delete(existingMovie);
    }
}