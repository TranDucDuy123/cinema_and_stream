package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    // Tìm thể loại theo ID
    Genre findById(int id);
}
