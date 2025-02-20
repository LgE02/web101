package com.example.service.MemberMission;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.mapping.MemberMission;
import com.example.web.dto.MemberMissionRequest;

import java.util.Optional;

public interface MemberMissionCommandService {
    MemberMission updateMissionStatus(Long memberId, Long missionId, MemberMissionRequest.JoinDto dto);
}
