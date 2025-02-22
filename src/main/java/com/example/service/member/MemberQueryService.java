package com.example.service.member;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.Review;
import com.example.domain.enums.MissionStatus;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    Page<Review> getReviews(Long MemberId, Integer page);
    Page<Mission> getMissions(Long MemberId, MissionStatus status, Integer page);
}
