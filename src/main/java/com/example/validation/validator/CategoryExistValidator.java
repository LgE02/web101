package com.example.validation.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.CategoryRepository;
import com.example.validation.annotaion.ExistsCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryExistValidator implements ConstraintValidator<ExistsCategories, List<Long>> {

    private final CategoryRepository categoryRepository;
    //private fina MemberCommandService memberCommandService;

    @Override
    public void initialize(ExistsCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> longs, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = longs.stream().allMatch(id -> categoryRepository.existsById(id));
        //수정부분

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.CATEGORY_NOT_FOUND.toString())
                    //해당 오류를 문자열로 반환
                    .addConstraintViolation();
        }

        return isValid;
    }
}
