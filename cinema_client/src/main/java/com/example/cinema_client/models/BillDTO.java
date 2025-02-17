package com.example.cinema_client.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BillDTO {
    private int id;
    private LocalDateTime createdTime;
    private List<TicketDTO> listTickets;
    private User user;
    private double price;  // Add this line
    private String formattedCreatedTime; 
}
