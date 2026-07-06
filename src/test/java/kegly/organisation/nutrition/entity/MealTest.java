package kegly.organisation.nutrition.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MealTest {

    @Test
    void calculateCcal_WhenDataIsGiven_ShouldReturn() {
        Integer protein = 2;
        Integer fat = 5;
        Integer carbohydrates = 2;
        Integer expectedCcal = 5 * 9 + 2 * 4 + 2 * 4;

        Meal meal = new Meal("name", protein, fat, carbohydrates);

        assertThat(meal.getCcal()).isEqualTo(expectedCcal);
    }
}