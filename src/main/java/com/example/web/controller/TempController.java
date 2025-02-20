package com.example.web.controller;

import com.example.converter.TempConverter;
import com.example.payload.CommonResponse;
import com.example.payload.status.SuccessStatus;
import com.example.service.temp.TempQueryService;
import com.example.web.dto.TempResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {

    //생성자 주입
    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public CommonResponse<TempResponse.TempTestDto> testAPI() {
        return CommonResponse.onSuccess(TempConverter.toTempTestDto("This is test string!"));
    }

    @GetMapping("/exception")
    public CommonResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        tempQueryService.checkFlag(flag);
        return CommonResponse.onSuccess(TempConverter.toTempExceptionDto(flag));
    }

    @GetMapping("/created")
    public CommonResponse<TempResponse.TempTestDto> createdAPI() {
        return CommonResponse.of(SuccessStatus.CREATED, TempConverter.toTempTestDto("This is test string!"));
    }


}
