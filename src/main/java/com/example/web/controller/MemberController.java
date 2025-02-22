package com.example.web.controller;

import com.example.converter.MemberConverter;
import com.example.converter.MemberMissionConverter;
import com.example.converter.MissionConverter;
import com.example.converter.ReviewConverter;
import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.Review;
import com.example.domain.enums.MissionStatus;
import com.example.domain.mapping.MemberMission;
import com.example.payload.CommonResponse;
import com.example.service.MemberMission.MemberMissionCommandService;
import com.example.service.member.MemberCommandService;
import com.example.service.member.MemberQueryService;
import com.example.validation.annotaion.CheckPage;
import com.example.validation.annotaion.ExistsMember;
import com.example.validation.annotaion.ExistsMission;
import com.example.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberQueryService memberQueryService;
    //인터페이스를 의존하면서 의존성 주입
    //실제 구현체를 몰라도 동작이 가능하다
    //구현체의 결합도를 낮춘다.

    @PostMapping("/")
    @Operation(summary = "회원가입API")
    public CommonResponse<MemberResponse.JoinResultDto> join(@RequestBody @Valid MemberRequest.JoinDto dto){
        //api 요청을 받는 join메소드
        //요청에 대한 api 공동 응답을 반환타입으로 가짐
        Member member = memberCommandService.join(dto);
        return CommonResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

    @PatchMapping("/{id}/missions/{missionId}")
    @Operation(summary = "가게 미션을 도전 중인 미션에 추가 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "COMMON_200", description = "성공"),
            @ApiResponse(responseCode = "STORE_4001", description = "가게를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "MEMBER_4001", description = "멤버를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "멤버 id", required = true),
            @Parameter(name = "missionId", description = "미션 id", required = true)
    })
    public CommonResponse<MemberMissionResponse.JoinResultDto> join(
            @ExistsMember @PathVariable(name = "id") Long memberId,
            @ExistsMission @PathVariable(name = "missionId") Long missionId,
            @RequestBody @Valid MemberMissionRequest.JoinDto dto){
            MemberMission memberMission = memberMissionCommandService.updateMissionStatus(memberId, missionId, dto);
        return CommonResponse.onSuccess(MemberMissionConverter.toMemberMissionResponseDto(memberMission));
    }

    @GetMapping("/{id}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 API")
    @Parameters(value = {
            @Parameter(name ="id",  description = "멤버 id", required = true)
    })
    public CommonResponse<ReviewResponse.ReviewListDto> getmemberReivews(
            @ExistsMember @PathVariable (name="id") Long memberId,
            @CheckPage @RequestParam(name = "page", defaultValue = "0") Integer page){
        Page<Review> review = memberQueryService.getReviews(memberId, page);

        return CommonResponse.onSuccess(ReviewConverter.toReviewListDto(review));
    }

    @GetMapping("/{id}/mission")
    @Operation(summary = "내가 진행 중인 미션 목록")
    @Parameters(value = {
            @Parameter(name="id", description = "멤버 ID", required = true)
    })
    public CommonResponse<MissionResponse.MissionListDto> getmemberMission(
            @ExistsMember @PathVariable(name = "id") Long memberId,
                        @RequestParam(name ="status" ) MissionStatus status,
            @CheckPage @RequestParam(name = "page", defaultValue = "0") Integer page){
        Page<Mission> mission = memberQueryService.getMissions(memberId, status, page);
        return CommonResponse.onSuccess(MissionConverter.toMissionListDto(mission));
    }

}
