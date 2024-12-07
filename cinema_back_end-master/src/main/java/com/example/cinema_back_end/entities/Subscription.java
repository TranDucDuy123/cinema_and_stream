package com.example.cinema_back_end.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;  // Tên gói (ví dụ: "SD", "HD", "4K")
    private double price;  // Giá của gói
    private String quality;  // Chất lượng stream (SD, HD, 4K)
}
