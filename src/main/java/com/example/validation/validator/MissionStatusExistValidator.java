package com.example.validation.validator;

import com.example.domain.enums.MissionStatus;
import com.example.payload.status.ErrorStatus;
import com.example.service.MemberMission.MemberMissionCommandService;
import com.example.service.MemberMission.MemberMissionCommandServiceImpl;
import com.example.validation.annotaion.ExistsMissionStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionStatusExistValidator implements ConstraintValidator<ExistsMissionStatus, MissionStatus> {

    private final MemberMissionCommandServiceImpl memberMissionCommandService;

    @Override
    public void initialize(ExistsMissionStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionStatus status, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = memberMissionCommandService.checkStatus(status);

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_WORK.toString())
                    //해당 오류를 문자열로 반환
                    .addConstraintViolation();
        }
        return isValid;
    }
}
