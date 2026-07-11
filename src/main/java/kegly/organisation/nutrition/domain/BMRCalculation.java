package kegly.organisation.nutrition.domain;

import kegly.organisation.nutrition.entity.Sex;
import kegly.organisation.nutrition.entity.User;

import static kegly.organisation.nutrition.domain.constants.BMRConstants.*;

public class BMRCalculation {
    public static Integer calculateBMR(User user){
        if(user.getSex().equals(Sex.MALE)){
            return calculateMaleBMR(user);
        }
        return calculateFemaleBMR(user);
    }

    private static Integer calculateMaleBMR(User user){
        return (int) ( WEIGHT_CONSTANT * user.getWeight() + HEIGHT_CONSTANT* user.getHeight()
                - AGE_CONSTANT * user.getAge() + MALE_CONSTANT);
    }

    private static Integer calculateFemaleBMR(User user){
        return (int)(WEIGHT_CONSTANT * user.getWeight() + HEIGHT_CONSTANT* user.getHeight()
                - AGE_CONSTANT * user.getAge() - FEMALE_CONSTANT);
    }
}
