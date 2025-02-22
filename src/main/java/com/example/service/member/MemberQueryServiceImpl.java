package com.example.service.member;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.Review;
import com.example.domain.enums.MissionStatus;
import com.example.domain.mapping.MemberMission;
import com.example.repository.MemberMissionRepository;
import com.example.repository.MemberRepository;
import com.example.repository.MissionRepository;
import com.example.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    @Override
    public Page<Review> getReviews(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId).get();
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }

    @Override
    public Page<Mission> getMissions(Long memberId, MissionStatus status, Integer page){
        Page<MemberMission> memberMissions = memberMissionRepository.findByMemberIdAndStatus(
                memberId, status, PageRequest.of(page, 10)
        );

        return memberMissions.map(MemberMission::getMission);
    //@Query 사용시
//        Member member = memberRepository.findById(memberId).get();
//        return memberMissionRepository.findMissionsByMemberAndStatus(memberId, status,PageRequest.of(page, 10));

    }

}
