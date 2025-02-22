package com.example.converter;

import com.example.domain.Mission;
import com.example.web.dto.MissionResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {
    public static MissionResponse.SimpleMissionDto toSimpleMissionDto(Mission mission){
        return MissionResponse.SimpleMissionDto.builder()
                .store(mission.getStore().getName())
                .point(mission.getPoint())
                .body(mission.getBody())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponse.MissionListDto toMissionListDto(Page<Mission> missions){
        List<MissionResponse.SimpleMissionDto> missionDtos = missions.getContent().stream()
                .map(mission -> MissionConverter.toSimpleMissionDto(mission))
                .toList();

        return MissionResponse.MissionListDto.builder()
                .isLast(missions.isLast())
                .isFirst(missions.isFirst())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .listSize(missionDtos.size())
                .missions(missionDtos)
                .build();
    }
}
