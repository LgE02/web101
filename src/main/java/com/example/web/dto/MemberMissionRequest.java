package com.example.web.dto;

import com.example.domain.enums.MissionStatus;
import com.example.validation.annotaion.ExistsMissionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberMissionRequest {
    @Getter
    public static class JoinDto {
        @ExistsMissionStatus
        private MissionStatus status;
    }
}
