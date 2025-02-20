package com.example.service.review;

import com.example.domain.Review;
import com.example.web.dto.ReviewRequest;

public interface ReviewCommandService {
    Review join(ReviewRequest.JoinDto dto);
    // join이 리뷰 생성을 수행한 후 저장된 Review 엔티티를 반환
    // DB에서 review 객체를 생성하고 반환하는 역할

}
