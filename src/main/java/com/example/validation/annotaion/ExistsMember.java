package com.example.validation.annotaion;

import com.example.validation.validator.MemberExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {MemberExistValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsMember {
    String message() default "해당하는 멤버는 존재하지 않습니다.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
