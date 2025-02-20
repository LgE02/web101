package com.example.web.controller;

import com.example.converter.ReviewConverter;
import com.example.domain.Review;
import com.example.payload.CommonResponse;
import com.example.service.review.ReviewCommandService;
import com.example.web.dto.ReviewRequest;
import com.example.web.dto.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public CommonResponse<ReviewResponse.JoinResultDto> join(@RequestBody @Valid ReviewRequest.JoinDto dto){
        Review review = reviewCommandService.join(dto);
        return CommonResponse.onSuccess(ReviewConverter.toJoinResultDto(review));
    }
}
