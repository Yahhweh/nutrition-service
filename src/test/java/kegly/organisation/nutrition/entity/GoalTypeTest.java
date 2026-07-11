package kegly.organisation.nutrition.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GoalTypeTest {

    @Test
    void shouldCalculateWeeklyLeanBulkWeightGain() {
        GoalType goalType = GoalType.LEAN_BULK;
        double weight = 82;
        double expectedGain = weight * 0.0035;

        double result = goalType.weeklyChangeKg(weight);

        assertThat(result).isEqualTo(expectedGain);
    }

    @Test
    void shouldCalculateDailyProteinForLeanBulk() {
        GoalType goalType = GoalType.LEAN_BULK;
        double weight = 82;
        double expectedProteinPerDay = weight * 2;

        double protein = goalType.proteinGrams(weight);

        assertThat(protein).isEqualTo(expectedProteinPerDay);
    }

    @Test
    void shouldCalculateDailyFatForLeanBulk() {
        GoalType goalType = GoalType.LEAN_BULK;
        double weight = 82;
        double expectedFatPerDay = weight * 0.9;

        double fat = goalType.fatGrams(weight);

        assertThat(fat).isEqualTo(expectedFatPerDay);
    }
}