package com.example.validation.annotaion;

import com.example.domain.enums.MissionStatus;
import com.example.validation.validator.MemberExistValidator;
import com.example.validation.validator.MissionStatusExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {MissionStatusExistValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsMissionStatus {
    String message() default "해당하는 미션은 이미 진행중입니다..";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
