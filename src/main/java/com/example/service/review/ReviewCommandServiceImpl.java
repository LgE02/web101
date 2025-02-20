package com.example.service.review;

import com.example.converter.ReviewConverter;
import com.example.domain.Member;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.exception.handler.MemberHandler;
import com.example.exception.handler.StoreHandler;
import com.example.payload.status.ErrorStatus;
import com.example.repository.MemberRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.StoreRepository;
import com.example.web.dto.ReviewRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;


    public boolean memberExistsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }

    public boolean storeExistsById(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Review join(ReviewRequest.JoinDto dto){ //생성

        //회원 있는지 확인
        Member member = memberRepository.findById(dto.getMemberId()).get();

        //가게 확인
        Store store = storeRepository.findById(dto.getStoreId()).get();

        Review newReview = ReviewConverter.toReview(dto,member,store);
        return reviewRepository.save(newReview);
    }
}
