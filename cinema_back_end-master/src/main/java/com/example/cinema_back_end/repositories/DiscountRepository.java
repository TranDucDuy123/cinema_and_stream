package com.example.cinema_back_end.repositories;

import com.example.cinema_back_end.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    List<Discount> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<Discount> findByPercentage(Double percentage);
    // Thêm phương thức để tìm kiếm giảm giá theo mã code
    Discount findByCode(String code);
}
