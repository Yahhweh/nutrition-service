package kegly.organisation.nutrition.domain;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.entity.Activity;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NutritionCalculator {
    private final UserService userService;

    public Target calculateNutritionCalculator(TargetRequestDto requestDto) {
        User user = userService.findByTelegramId(requestDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        var type = requestDto.getType();
        double weight = user.getWeight();

        int targetCcal = user.calculateTDEE()
                + adjustCcal(type.weeklyChangeKg(weight));
        int protein = (int) type.proteinGrams(weight);
        int fat = (int) type.fatGrams(weight);

        return Target.of(type, targetCcal, protein, fat);
    }

    public Integer adjustCcal(Double weeklyChangeKg){
        return (int) (weeklyChangeKg * 7700 / 7);
    }
}
