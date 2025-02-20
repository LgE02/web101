package com.example.service.member;

import com.example.domain.Member;
import com.example.web.dto.MemberRequest;

public interface MemberCommandService {
    Member join(MemberRequest.JoinDto dto);
    //회원가입을 처리하는 서비스 메서드
    // join이 회원 가입을 수행한 후 저장된 Member 엔티티를 반환
    // DB에서 Member 객체를 생성하고 반환하는 역할
    //컨트롤러의 join과 다름 -> impl에서 구현
}
