package com.example.converter;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.mapping.MemberMission;
import com.example.web.dto.MemberMissionRequest;
import com.example.web.dto.MemberMissionResponse;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    //응답객체
    //PATH로 업데이트이니깐 객체 생성은 노
    public static MemberMissionResponse.JoinResultDto toMemberMissionResponseDto(MemberMission memberMission) {
        return MemberMissionResponse.JoinResultDto.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus())
                .updatedAt(LocalDateTime.now())
                .build();
    }


}
