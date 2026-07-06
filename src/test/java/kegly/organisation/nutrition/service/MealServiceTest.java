package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.MealRequestDto;
import kegly.organisation.nutrition.entity.Food;
import kegly.organisation.nutrition.entity.Meal;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.repository.FoodRepository;
import kegly.organisation.nutrition.repository.MealRepository;
import kegly.organisation.nutrition.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private MealService mealService;

    @Test
    void create_whenValidDto_thenMealIsSaved() {
        MealRequestDto dto = buildDto();
        User user = buildUser();
        Food food = buildFood();
        Meal saved = buildMeal(user, food);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(foodRepository.findById(10L)).thenReturn(Optional.of(food));
        when(mealRepository.save(any(Meal.class))).thenReturn(saved);

        Meal result = mealService.create(dto);

        assertThat(result).isEqualTo(saved);
        verify(userRepository).findById(1L);
        verify(foodRepository).findById(10L);
        verify(mealRepository).save(any(Meal.class));
    }

    @Test
    void create_whenUserNotFound_thenThrowEntityNotFoundException() {
        MealRequestDto dto = buildDto();
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealService.create(dto));

        verify(mealRepository, never()).save(any());
    }

    @Test
    void create_whenFoodNotFound_thenThrowEntityNotFoundException() {
        MealRequestDto dto = buildDto();
        when(userRepository.findById(1L)).thenReturn(Optional.of(buildUser()));
        when(foodRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealService.create(dto));

        verify(mealRepository, never()).save(any());
    }

    @Test
    void getById_whenMealExists_thenReturnMeal() {
        Meal meal = buildMeal(buildUser(), buildFood());
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        Meal result = mealService.getById(1L);

        assertThat(result).isEqualTo(meal);
    }

    @Test
    void getById_whenMealNotFound_thenThrowEntityNotFoundException() {
        when(mealRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealService.getById(99L));
    }

    @Test
    void findByUserId_whenCalled_thenReturnUserMeals() {
        Meal meal = buildMeal(buildUser(), buildFood());
        when(mealRepository.findByUserId(1L)).thenReturn(List.of(meal));

        List<Meal> result = mealService.findByUserId(1L);

        assertThat(result).hasSize(1).contains(meal);
    }

    @Test
    void delete_whenCalled_thenRepositoryDeleteByIdIsCalled() {
        mealService.delete(1L);

        verify(mealRepository).deleteById(1L);
    }

    private MealRequestDto buildDto() {
        return new MealRequestDto.Builder()
                .userId(1L)
                .foodId(10L)
                .build();
    }

    private User buildUser() {
        User user = new User();
        user.setId(1L);
        user.setTelegramId(100L);
        user.setUsername("testUser");
        return user;
    }

    private Food buildFood() {
        return new Food.Builder()
                .id(10L)
                .name("Chicken")
                .protein(40)
                .fat(10)
                .carbohydrates(0)
                .build();
    }

    private Meal buildMeal(User user, Food food) {
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setUser(user);
        meal.setFood(food);
        return meal;
    }
}