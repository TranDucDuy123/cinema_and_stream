package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.StreamTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StreamTicketRepository extends JpaRepository<StreamTicket, Integer> {
    List<StreamTicket> findByUserId(Integer userId);
    List<StreamTicket> findByMovieId(Integer movieId);
}