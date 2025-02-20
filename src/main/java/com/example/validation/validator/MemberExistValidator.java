package com.example.validation.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.MemberMissionRepository;
import com.example.repository.MemberRepository;
import com.example.service.MemberMission.MemberMissionCommandServiceImpl;
import com.example.service.review.ReviewCommandServiceImpl;
import com.example.validation.annotaion.ExistsMember;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistsMember, Long> {
    private final ReviewCommandServiceImpl reviewCommandService;


    @Override
    public void initialize(ExistsMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = reviewCommandService.memberExistsById(aLong);

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
                    //해당 오류를 문자열로 반환
                    .addConstraintViolation();
        }

        return isValid;
    }

//    @Override
//    public boolean isValid2(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
//        boolean isValid2 = memberMissionCommandService.memberExistsById(aLong);
//
//        if(!isValid2) {
//            constraintValidatorContext.disableDefaultConstraintViolation();
//            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
//                    //해당 오류를 문자열로 반환
//                    .addConstraintViolation();
//        }
//
//        return isValid2;
//    }
}
