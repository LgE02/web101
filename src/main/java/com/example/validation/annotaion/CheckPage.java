package com.example.validation.annotaion;

import com.example.validation.validator.CategoryExistValidator;
import com.example.validation.validator.CheckPageExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CheckPageExistValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "페이지 번호는 0 이상이어야 합니다.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
