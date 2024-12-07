package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Subscription findByName(String name);
}