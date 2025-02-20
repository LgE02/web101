package com.example.converter;

import com.example.domain.Member;
import com.example.domain.enums.Gender;
import com.example.web.dto.MemberRequest;
import com.example.web.dto.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class MemberConverter {
    //응답을 받고 DTO에서 Entity 변환
    //요청을 받았을때의 응답 객체 생성
    public static MemberResponse.JoinResultDto toJoinResultDto(Member member) {
        return MemberResponse.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //요청시 dto 값을 entity로 변환하고 해당 값으로 새로운 멤버 객체 셍성
    public static Member toMember(MemberRequest.JoinDto dto){
        Gender gender;

        try{
            gender = Gender.valueOf(dto.getGender());
            //받아온 dto 값을 entity값으로 전환
        }catch(IllegalArgumentException e){
            gender = Gender.NO_ANSWER;
        }

        return Member.builder() //빌더를 이용해서 새로운 멤버 객체 생성
                .name(dto.getName())
                .age(LocalDateTime.now().getYear() - dto.getBirthYear())
                .address(dto.getAddress())
                .spec_address(dto.getSpecAddress())
                .gender(gender)
                .memberPrefers(new ArrayList<>())
                .build();
    }

}
