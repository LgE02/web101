package com.example.validation.annotaion;

import com.example.validation.validator.StatusExistValidator;
import com.example.validation.validator.StoreExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StatusExistValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsStatus {
    String message() default "해당하는 미션 상태는 존재하지 않습니다.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
