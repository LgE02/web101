package com.example.service.store;

import com.example.domain.Mission;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.repository.MissionRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long storeId){
        return storeRepository.findById(storeId);
    }

    @Override
    public Page<Review> getReviews(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();

        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<Mission> getMissions(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();

        return missionRepository.findAllByStore(store, PageRequest.of(page, 10));
    }
}
