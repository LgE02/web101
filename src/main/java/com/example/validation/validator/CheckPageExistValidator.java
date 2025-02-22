package com.example.validation.validator;

import com.example.payload.status.ErrorStatus;
import com.example.validation.annotaion.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckPageExistValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (page == null || page < 0) {
            isValid=false;
        }

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NUM_ERROR.toString())
                    //해당 오류를 문자열로 반환
                    .addConstraintViolation();
        }
        return isValid;
    }
}
