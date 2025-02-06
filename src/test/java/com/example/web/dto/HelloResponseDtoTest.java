package com.example.web.dto;

import com.example.web.DTO.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        //assertj라는 테스트 검증 라이브러리의 메서드
        //검증하고 싶은 대상을 인자로 받는다.
        //isEqualTo : assertj의 동등 비교 메서드
    }
}
