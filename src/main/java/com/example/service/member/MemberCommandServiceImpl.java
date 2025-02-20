package com.example.service.member;

import com.example.converter.MemberConverter;
import com.example.converter.MemberPreferConverter;
import com.example.domain.Category;
import com.example.domain.Member;
import com.example.domain.mapping.MemberPrefer;
import com.example.exception.handler.CategoryHandler;
import com.example.payload.status.ErrorStatus;
import com.example.repository.CategoryRepository;
import com.example.repository.MemberRepository;
import com.example.web.dto.MemberRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    //레포지포리의 의존관계 주입
    //Service가 Repository를 의존
    private final CategoryRepository categoryRepository;
    //의존관계 주입

    @Override
    public Member join(MemberRequest.JoinDto dto){
        Member newMember = MemberConverter.toMember(dto);
        //회원가입 요청이 들어오면 dto를 Member 엔티티로 변환

        // 2. 사용자가 선택한 카테고리 ID들을 실제 Category 엔티티로 변환
//        List<Category> categories = dto.getPreferCategory().stream()
//                .map(category -> categoryRepository.findById(category)
//                    .orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND)))
//                .toList();

        //validator 검사를 이용한 재 작성
        List<Category> categories = dto.getPreferCategory().stream()
                .map(category -> categoryRepository.findById(category).get())
                .toList();

        //categories 리스트를 MemberPrefer 리스트로 변환
        //MemberPrefer 객체 생성
        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPrefers(categories);
        memberPrefers.forEach(memberPrefer -> memberPrefer.setMember(newMember));

        return memberRepository.save(newMember); //레포지토리에 저장
    }
}
