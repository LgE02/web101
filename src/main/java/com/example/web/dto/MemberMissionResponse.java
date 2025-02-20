package com.example.web.dto;

import com.example.domain.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberMissionResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDto{
        private Long memberId;
        private Long missionId;
        private MissionStatus status;
        private LocalDateTime updatedAt;
    }
}
