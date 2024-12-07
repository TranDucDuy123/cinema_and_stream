package com.example.cinema_back_end.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(length = 1000)
    private String smallImageURl;

    @Column(length = 1000)
    private String shortDescription;

    @Column(length = 1000)
    private String longDescription;

    @Column(length = 1000)
    private String largeImageURL;

    private String director;
    private String actors;
    private String categories; // Các thể loại chính

    private LocalDate releaseDate;
    private int duration;  // Dành cho thời gian chiếu tại rạp
    private int streamDuration;  // Thời gian xem stream (nếu có khác nhau)

    private String trailerURL;
    private String language;
    private String rated;

    private int isShowing;  // Dành cho các phim chiếu rạp
    private boolean isStreamable;  // Xác định phim có thể stream hay không

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;  // Các thể loại của phim (gợi ý theo thể loại yêu thích của người dùng)

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList; // Các lịch chiếu phim (cho chiếu rạp)
}

