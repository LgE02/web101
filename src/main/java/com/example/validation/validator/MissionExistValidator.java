package com.example.validation.validator;

import com.example.payload.status.ErrorStatus;
import com.example.service.MemberMission.MemberMissionCommandServiceImpl;
import com.example.validation.annotaion.ExistsMember;
import com.example.validation.annotaion.ExistsMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistsMission, Long> {

    private final MemberMissionCommandServiceImpl memberMissionCommandService;

    @Override
    public void initialize(ExistsMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = memberMissionCommandService.missionExistsById(aLong);

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString())
                    //해당 오류를 문자열로 반환
                    .addConstraintViolation();
        }

        return isValid;
    }
}
