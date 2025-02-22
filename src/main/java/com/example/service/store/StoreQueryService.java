package com.example.service.store;

import com.example.domain.Mission;
import com.example.domain.Review;
import com.example.domain.Store;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Page<Review> getReviews(Long StoreId, Integer page);
    //Page는 Spring Data JPA에서 제공하는 Paging을 위한 추상화를 제공
    Page<Mission> getMissions(Long StoreId, Integer page);
}
