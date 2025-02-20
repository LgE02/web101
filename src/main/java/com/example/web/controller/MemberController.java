package com.example.web.controller;

import com.example.converter.MemberConverter;
import com.example.converter.MemberMissionConverter;
import com.example.domain.Member;
import com.example.domain.mapping.MemberMission;
import com.example.payload.CommonResponse;
import com.example.service.MemberMission.MemberMissionCommandService;
import com.example.service.member.MemberCommandService;
import com.example.web.dto.MemberMissionRequest;
import com.example.web.dto.MemberMissionResponse;
import com.example.web.dto.MemberRequest;
import com.example.web.dto.MemberResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberMissionCommandService memberMissionCommandService;
    //인터페이스를 의존하면서 의존성 주입
    //실제 구현체를 몰라도 동작이 가능하다
    //구현체의 결합도를 낮춘다.

    @PostMapping("/")
    public CommonResponse<MemberResponse.JoinResultDto> join(@RequestBody @Valid MemberRequest.JoinDto dto){
        //api 요청을 받는 join메소드
        //요청에 대한 api 공동 응답을 반환타입으로 가짐
        Member member = memberCommandService.join(dto);
        return CommonResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

    @PatchMapping("/{memberId}/missions/{missionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "COMMON_200", description = "성공"),
            @ApiResponse(responseCode = "STORE_4001", description = "가게를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "MEMBER_4001", description = "멤버를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
    })
    @Parameters(value = {
            @Parameter(name = "memberId", description = "멤버 id", required = true),
            @Parameter(name = "missionId", description = "미션 id", required = true)
    })
    public CommonResponse<MemberMissionResponse.JoinResultDto> join(
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody @Valid MemberMissionRequest.JoinDto dto){
            MemberMission memberMission = memberMissionCommandService.updateMissionStatus(memberId, missionId, dto);
        return CommonResponse.onSuccess(MemberMissionConverter.toMemberMissionResponseDto(memberMission));
    }
}
