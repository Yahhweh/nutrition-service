package kegly.organisation.nutrition.domain;

import kegly.organisation.nutrition.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class NutritionCalculatorTest {
    @Mock
    UserService userService;
    @InjectMocks
    NutritionCalculator nutritionCalculator;

    @Test
    void shouldCalculateDailyCalorieAdjustmentFromWeeklyWeightChange() {

        double weeklyChangeKg = 0.5;
        int expectedCalories = (int) (weeklyChangeKg * 7700 / 7);

        int result = nutritionCalculator.adjustCcal(weeklyChangeKg);

        assertThat(result).isEqualTo(expectedCalories);
    }

}