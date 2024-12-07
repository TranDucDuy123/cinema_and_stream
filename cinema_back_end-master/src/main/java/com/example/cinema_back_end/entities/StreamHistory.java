package com.example.cinema_back_end.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "stream_history")
public class StreamHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stream_ticket_id", nullable = false)
    private StreamTicket streamTicket;  // Vé stream liên kết với lịch sử xem

    private LocalDateTime watchStartTime;  // Thời gian bắt đầu xem
    private LocalDateTime watchEndTime;  // Thời gian kết thúc xem

    private String quality;  // Chất lượng xem (SD, HD, 4K)

    private boolean isCompleted;  // Trạng thái xem (hoàn thành hoặc dừng giữa chừng)
}
