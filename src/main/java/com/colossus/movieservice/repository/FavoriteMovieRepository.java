package com.colossus.movieservice.repository;

import com.colossus.movieservice.entity.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
}
