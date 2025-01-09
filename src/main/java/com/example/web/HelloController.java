package com.example.web;

import com.example.web.DTO.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Controller가 json을 반환하도록 함.
public class HelloController {
    @GetMapping("/hello")
    // HTTP의 GET 메소드를 요청받을수 있는 API를 생성
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto hello(@RequestParam String name,
                                  @RequestParam int amount) {
        //@RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        //여기서는 name으로 넘김 쿼리 파라미터를 가져옴
        return new HelloResponseDto(name, amount);
    }

}
