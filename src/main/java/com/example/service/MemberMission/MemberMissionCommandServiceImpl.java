package com.example.service.MemberMission;

import com.example.converter.MemberMissionConverter;
import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.enums.MemberStatus;
import com.example.domain.enums.MissionStatus;
import com.example.domain.mapping.MemberMission;
import com.example.exception.handler.MemberHandler;
import com.example.exception.handler.MissionHandler;
import com.example.payload.status.ErrorStatus;
import com.example.repository.MemberMissionRepository;
import com.example.repository.MemberRepository;
import com.example.repository.MissionRepository;
import com.example.web.dto.MemberMissionRequest;
import com.example.web.dto.MemberMissionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public boolean checkStatus(MissionStatus status) {
        return status == MissionStatus.PROGRESS;
    }
    public boolean memberExistsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }
    public boolean missionExistsById(Long missionId) {
        return missionRepository.existsById(missionId);
    }


    public MemberMission updateMissionStatus(Long memberId, Long missionId, MemberMissionRequest.JoinDto dto){
        //존재 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        //특정 멤버의 미션존재 확인
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_MEMBER));


        if (checkStatus(memberMission.getStatus())) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_WORK);
        }

        memberMission.updateStatus(dto.getStatus());

        return memberMissionRepository.save(memberMission);
    }
}
