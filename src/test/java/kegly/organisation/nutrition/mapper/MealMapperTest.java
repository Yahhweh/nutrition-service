package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.request.MealRequestDto;
import kegly.organisation.nutrition.entity.Meal;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

class MealMapperTest {
    MealMapper mealMapper = new MealMapperImpl();

    @Test
    void toEntity_ShouldReturnTrueWhenDtoAndEntityIsEqual() {
        Meal expected = getMeal();
        MealRequestDto dto = getDto();

        Meal real =  mealMapper.toEntity(dto);
        assertThat(real.getName()).isEqualTo(expected.getName());
        assertThat(real.getProtein()).isEqualTo(expected.getProtein());
        assertThat(real.getCarbohydrates()).isEqualTo(expected.getCarbohydrates());
        assertThat(real.getFat()).isEqualTo(expected.getFat());
        assertThat(real.getCcal()).isEqualTo(216);
        assertThat(real.getCreatedAt()).isNotNull();

    }

    private Meal getMeal(){
        return new Meal.Builder()
                .name("Chicken")
                .protein(40)
                .fat(4)
                .carbohydrates(5)
                .build();
    }

    private MealRequestDto getDto(){
        return new MealRequestDto.Builder()
                .name("Chicken")
                .protein(40)
                .fat(4)
                .carbohydrates(5)
                .build();
    }
}