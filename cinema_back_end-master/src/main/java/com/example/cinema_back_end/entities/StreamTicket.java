package com.example.cinema_back_end.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@Table(name = "stream_ticket")
public class StreamTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;  // Phim được chọn để xem stream

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;  // Gói stream (SD, HD, 4K)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Người dùng đã mua vé stream

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;  // Hóa đơn liên kết với vé stream

    private LocalDateTime purchaseDate;  // Ngày giờ mua vé

    private boolean isActive;  // Trạng thái vé (đang hoạt động hay không)
}
