package com.example.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
//스프링 부트 테스트와 JUnit 사이의 연결자 역할
@WebMvcTest
//스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중
class HelloControllerTest {

    @Autowired
    //스프링이 관리하는 빈 주입
    private MockMvc mvc;
    //웹 API를 테스트할 때 사용, 가짜MVC

    @Test
    void hello가_리턴된다() throws Exception {
        String hello = "Hello, Spring Boot!";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 GET요청
                .andExpect(status().isOk())
                //HTTP Header의 상태를 검증하는 역할
                .andExpect(content().string(hello));
                //Controller에서 리턴하는 문자열이 "Hello, Spring Boot!"이 맞는지 검증
    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "Hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                )
                //parm : API 테스트시 사용될 요청 파라미터를 설정. String만 허용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));
                //jsonPath :  JSON 응답값을 필드별로 검증. $를 기준으로 필드명을 명시
    }
}