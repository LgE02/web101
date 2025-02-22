package com.example.payload.status;

import com.example.payload.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseStatus {
    // Common Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON_400","잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON_401","인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "금지된 요청입니다."),

    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER_4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER_4002", "닉네임은 필수 입니다."),

    // Article Error
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE_4001", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP_4001", "이것은 테스트"),

    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "CATEGORY_4001", "카테고리가 없습니다."),
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "CATEGORY_4001","가게가 없습니다"),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION_4001","미션이 없습니다"),
    MISSION_ALREADY_WORK(HttpStatus.BAD_REQUEST, "MISSION_4002","이미 진행중인 미션입니다."),
    MISSION_NOT_MEMBER(HttpStatus.BAD_REQUEST, "MISSION_4003","해당 미션신청을 받아오지 못했습니다."),

    PAGE_NUM_ERROR(HttpStatus.BAD_REQUEST, "MISSION_4001","페이지 번호는 0이상이여야 합니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .httpStatus(httpStatus)
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }
}
