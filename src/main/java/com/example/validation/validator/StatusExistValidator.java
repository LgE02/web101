package com.example.validation.validator;

import com.example.domain.Mission;
import com.example.domain.enums.MissionStatus;
import com.example.validation.annotaion.ExistsStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusExistValidator implements ConstraintValidator<ExistsStatus, MissionStatus> {
    @Override
    public void initialize(ExistsStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionStatus missionStatus, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;

        if(missionStatus ==null)
            isValid = false;
        if(missionStatus.equals("WAITING") || missionStatus.equals("PROGRESS") || missionStatus.equals("COMPLETE")){
           isValid = true;
        }

        return isValid;
    }
}
