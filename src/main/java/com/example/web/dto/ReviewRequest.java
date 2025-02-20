package com.example.web.dto;

import com.example.validation.annotaion.ExistsMember;
import com.example.validation.annotaion.ExistsStore;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ReviewRequest {
    @Getter
    public static class JoinDto {
        @ExistsMember
        private Long memberId;
        @ExistsStore
        private Long storeId;
        @DecimalMin("0.0")  // 최소 1.0점
        @DecimalMax("5.0")
        private Double score;
        @NotBlank
        private String title;
        @NotBlank
        private String body;

    }
}
