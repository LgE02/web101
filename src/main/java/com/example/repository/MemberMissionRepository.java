package com.example.repository;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.enums.MissionStatus;
import com.example.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
    //특정 회원의 미션조회
//    @Query("SELECT mm.mission FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
//    Page<Mission> findMissionsByMemberAndStatus(@Param("memberId") Long memberId,
//                                                @Param("status") MissionStatus status,
//                                                PageRequest pageRequest);
    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status, PageRequest pageRequest);
}
