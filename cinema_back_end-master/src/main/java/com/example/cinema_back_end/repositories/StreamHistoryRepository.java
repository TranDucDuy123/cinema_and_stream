package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.StreamHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreamHistoryRepository extends JpaRepository<StreamHistory, Integer> {
    List<StreamHistory> findByStreamTicketId(Integer streamTicketId);
}
