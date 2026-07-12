package kegly.organisation.nutrition.domain;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.entity.GoalType;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NutritionCalculatorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private NutritionCalculator calculator;

    private static final long TELEGRAM_ID = 42L;
    private static final double WEIGHT = 80.0;
    private static final int TDEE = 2500;
    private static final GoalType TYPE = GoalType.CUTTING;

    private TargetRequestDto requestDto() {
        TargetRequestDto dto = new TargetRequestDto();
        dto.setUserId(TELEGRAM_ID);
        dto.setType(TYPE);
        return dto;
    }

    private User mockUser() {
        User user = org.mockito.Mockito.mock(User.class);
        when(user.getWeight()).thenReturn(WEIGHT);
        when(user.calculateTDEE()).thenReturn(TDEE);
        return user;
    }

    @Test
    void returnsTargetWithComputedValues() {
        User user = mockUser();
        when(userService.findByTelegramId(TELEGRAM_ID))
                .thenReturn(Optional.of(user));

        int expectedCcal = TDEE + (int) (TYPE.weeklyChangeKg(WEIGHT) * 7700 / 7);
        int expectedProtein = (int) TYPE.proteinGrams(WEIGHT);
        int expectedFat = (int) TYPE.fatGrams(WEIGHT);
        int expectedCarbs = (expectedCcal - expectedProtein * 4 - expectedFat * 9) / 4;

        Target result = calculator.calculateNutritionCalculator(requestDto());

        assertThat(result).isNotNull();
        assertThat(result.getCcal()).isEqualTo(expectedCcal);
        assertThat(result.getProtein()).isEqualTo(expectedProtein);
        assertThat(result.getFat()).isEqualTo(expectedFat);
        assertThat(result.getCarbohydrates()).isEqualTo(expectedCarbs);
        assertThat(result.getType()).isEqualTo(TYPE);
    }

    @Test
    void addsAdjustmentOnTopOfTdee() {
        User user = mockUser();
        when(userService.findByTelegramId(TELEGRAM_ID))
                .thenReturn(Optional.of(user));

        Target result = calculator.calculateNutritionCalculator(requestDto());

        int adjustment = (int) (TYPE.weeklyChangeKg(WEIGHT) * 7700 / 7);
        assertThat(result.getCcal()).isEqualTo(TDEE + adjustment);
    }

    @Test
    void throwsWhenUserNotFound() {
        when(userService.findByTelegramId(TELEGRAM_ID))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                calculator.calculateNutritionCalculator(requestDto()))
                .isInstanceOf(EntityNotFoundException.class);
    }
}