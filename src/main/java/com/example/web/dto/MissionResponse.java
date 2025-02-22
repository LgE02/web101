package com.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDto {
        List<SimpleMissionDto> missions;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleMissionDto {
        String store;
        String body;
        Integer point;
        LocalDate deadline;
    }
}
