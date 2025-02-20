package com.example.web.dto;

import com.example.validation.annotaion.ExistsCategories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class MemberRequest {
    @Getter
    public static class JoinDto {
        @NotBlank
        private String name;
        @NotBlank
        private String gender;
        @NotNull
        private Integer birthYear;
        @NotNull
        private Integer birthMonth;
        @NotNull
        private Integer birthDay;
        @Size(min = 5, max = 12)
        private String address;
        @Size(min = 5, max = 12)
        private String specAddress;
        @ExistsCategories
        List<Long> preferCategory;
    }
}
