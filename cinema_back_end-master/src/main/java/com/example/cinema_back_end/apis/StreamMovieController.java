package com.example.cinema_back_end.apis;

import com.example.cinema_back_end.entities.Movie;
import com.example.cinema_back_end.entities.StreamHistory;
import com.example.cinema_back_end.entities.StreamTicket;
import com.example.cinema_back_end.services.StreamMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stream-movie")
public class StreamMovieController {

    @Autowired
    private StreamMovieService streamMovieService;

    // Lấy các phim yêu thích của người dùng
    @GetMapping("/user/{userId}/favorite-movies")
    public ResponseEntity<List<Movie>> getMoviesByUserGenres(@PathVariable Integer userId) {
        List<Movie> movies = streamMovieService.findMoviesByUserGenres(userId);
        return ResponseEntity.ok(movies);
    }

    // Mua vé stream cho người dùng
    @PostMapping("/user/{userId}/purchase-stream-ticket/{movieId}/{subscriptionName}")
    public ResponseEntity<StreamTicket> purchaseStreamTicket(
            @PathVariable Integer userId,
            @PathVariable Integer movieId,
            @PathVariable String subscriptionName) {
        StreamTicket streamTicket = streamMovieService.purchaseStreamTicket(userId, movieId, subscriptionName);
        return ResponseEntity.ok(streamTicket);
    }

    // Ghi nhận lịch sử xem phim
    @PostMapping("/stream-ticket/{streamTicketId}/history")
    public ResponseEntity<StreamHistory> recordStreamHistory(
            @PathVariable Integer streamTicketId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime,
            @RequestParam String quality) {
        StreamHistory streamHistory = streamMovieService.recordStreamHistory(streamTicketId, startTime, endTime, quality);
        return ResponseEntity.ok(streamHistory);
    }
    // Lấy danh sách phim có thể stream (isStreamable = true)
    @GetMapping("/available")
    public ResponseEntity<List<Movie>> getStreamableMovies() {
        List<Movie> movies = streamMovieService.findStreamableMovies();
        return ResponseEntity.ok(movies);
    }
}
