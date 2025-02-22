package com.example.validation.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.StoreRepository;
import com.example.service.review.ReviewCommandServiceImpl;
import com.example.validation.annotaion.ExistsStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistsStore, Long> {

    private final ReviewCommandServiceImpl reviewCommandService;
    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistsStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = reviewCommandService.storeExistsById(storeId);

        //boolean isValid = storeId.allMatch(id -> storeRepository.existsById(id));
        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString())
                    //해당 오류를 문자열로 반환
                    .addConstraintViolation();
        }

        return isValid;
    }

}
