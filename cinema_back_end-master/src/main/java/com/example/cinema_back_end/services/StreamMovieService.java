package com.example.cinema_back_end.services;

import com.example.cinema_back_end.entities.*;
import com.example.cinema_back_end.repositories.*;
import com.example.cinema_back_end.security.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StreamMovieService {

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private StreamTicketRepository streamTicketRepository;

    @Autowired
    private StreamHistoryRepository streamHistoryRepository;

    @Autowired
    private UserGenreRepository userGenreRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Tìm các phim yêu thích của người dùng theo thể loại
    public List<Movie> findMoviesByUserGenres(Integer userId) {
        List<UserGenre> userGenres = userGenreRepository.findByUserId(userId);
        if (userGenres.isEmpty()) {
            throw new IllegalArgumentException("User has no favorite genres");
        }
        List<Genre> genres = userGenres.stream()
                .map(UserGenre::getGenre)
                .toList();
        return movieRepository.findMoviesByGenresIn(genres);
    }

    public StreamTicket purchaseStreamTicket(Integer userId, Integer movieId, String subscriptionName) {
        // Kiểm tra xem phim có tồn tại không
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        // Kiểm tra xem gói đăng ký có tồn tại không
        Subscription subscription = subscriptionRepository.findByName(subscriptionName);
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription not found");
        }

        // Kiểm tra xem người dùng có tồn tại không
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Tạo hóa đơn (Bill) cho giao dịch
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setPrice(subscription.getPrice());  // Giá vé từ gói đăng ký
        bill.setCreatedTime(LocalDateTime.now()); // Thời gian tạo hóa đơn
        bill = billRepository.save(bill); // Lưu hóa đơn vào cơ sở dữ liệu

        // Tạo vé stream (StreamTicket)
        StreamTicket streamTicket = new StreamTicket();
        streamTicket.setUser(user);
        streamTicket.setMovie(movie);
        streamTicket.setSubscription(subscription);
        streamTicket.setBill(bill);
        streamTicket.setPurchaseDate(LocalDateTime.now());
        streamTicket.setActive(true); // Mark as active

        // Lưu vé stream vào cơ sở dữ liệu
        return streamTicketRepository.save(streamTicket);
    }


    // Lịch sử xem phim
    public StreamHistory recordStreamHistory(Integer streamTicketId, LocalDateTime startTime, LocalDateTime endTime, String quality) {
        StreamTicket streamTicket = streamTicketRepository.findById(streamTicketId)
                .orElseThrow(() -> new IllegalArgumentException("StreamTicket not found"));

        StreamHistory streamHistory = new StreamHistory();
        streamHistory.setStreamTicket(streamTicket);
        streamHistory.setWatchStartTime(startTime);
        streamHistory.setWatchEndTime(endTime);
        streamHistory.setQuality(quality);
        streamHistory.setCompleted(endTime != null);

        return streamHistoryRepository.save(streamHistory);
    }
    // Lấy danh sách phim có thể stream (isStreamable = true)
    public List<Movie> findStreamableMovies() {
        return movieRepository.findByIsStreamableTrue();
    }

}
