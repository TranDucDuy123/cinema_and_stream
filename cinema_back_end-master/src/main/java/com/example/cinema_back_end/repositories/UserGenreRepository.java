package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Genre;
import com.example.cinema_back_end.entities.UserGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGenreRepository extends JpaRepository<UserGenre, Integer> {
    List<UserGenre> findByUserId(Integer userId);
}