package com.example.converter;

import com.example.domain.Member;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.web.dto.ReviewRequest;
import com.example.web.dto.ReviewResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {
    //조회가 왔을때의 응답
    //각 리뷰마다의 간단 화면 데이터 응답
    public static ReviewResponse.SimpleReviewDto toSimpleReviewDto(Review review) {
        return ReviewResponse.SimpleReviewDto.builder()
                .ownerNickname(review.getMember().getName()) //멤버의 이름을 가져옴
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    //요청이 왔을때 특정 리뷰의 목록 리스크
    public static ReviewResponse.ReviewListDto toReviewListDto(Page<Review> reviews) {
        List<ReviewResponse.SimpleReviewDto> reviewDtos = reviews.getContent().stream()
                .map(review -> ReviewConverter.toSimpleReviewDto(review))
                .toList();

        return ReviewResponse.ReviewListDto.builder()
                .isLast(reviews.isLast())
                .isFirst(reviews.isFirst())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .listSize(reviewDtos.size())
                .reviews(reviewDtos)
                .build();
    }

    //요청이왔을때의
        //요청을 받았을때의 응답 객체 생성
     public static ReviewResponse.JoinResultDto toJoinResultDto(Review review){
        return ReviewResponse.JoinResultDto.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .body(review.getBody())
                .createdAt(LocalDateTime.now())
                .build();
     }
     //요청시 dto 값을 entity로 변환하고 해당 값으로 새로운 멤버 객체 셍성
     public static Review toReview(ReviewRequest.JoinDto dto, Member member, Store store){
        return Review.builder()
                .member(member)
                .store(store)
                .score(dto.getScore())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
     }
}
