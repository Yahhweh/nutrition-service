package kegly.organisation.nutrition.domain;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.entity.Activity;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static kegly.organisation.nutrition.domain.BMRCalculation.calculateBMR;

@Service
@AllArgsConstructor
public class NutritionCalculator {
    private final UserService userService;

//    public Target calculateNutritionCalculator(TargetRequestDto requestDto) {
//        User user = userService.findByTelegramId(requestDto.getUserId())
//                .orElseThrow(EntityNotFoundException::new);
//
//        Integer bmr = calculateBMR(user);
//        Integer tdee = calculateTDEE(bmr, user.getActivity());
//        Integer adjustCcal = adjustCcal(requestDto.getType().weeklyChangeKg(user.getWeight()));
//
//    }

    private Integer calculateTDEE(Integer bmr, Activity activity){
        return (int) (bmr * activity.getActivityMultiplier());
    }

    public Integer adjustCcal(Double weeklyChangeKg){
        return (int) (weeklyChangeKg * 7700 / 7);
    }
}
