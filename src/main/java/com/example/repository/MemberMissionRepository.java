package com.example.repository;

import com.example.domain.Member;
import com.example.domain.enums.MissionStatus;
import com.example.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
    //특정 회원의 미션조회
}
