package com.example.repository;

import com.example.domain.Member;
import com.example.domain.Review;
import com.example.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    //PageRequest는 페이징과 관련된 옵션
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
