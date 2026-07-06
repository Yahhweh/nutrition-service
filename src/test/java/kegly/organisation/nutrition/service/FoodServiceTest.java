package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.FoodRequestDto;
import kegly.organisation.nutrition.entity.Food;
import kegly.organisation.nutrition.mapper.FoodMapper;
import kegly.organisation.nutrition.repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodMapper foodMapper;

    @InjectMocks
    private FoodService foodService;

    @Test
    void create_whenValidDto_thenFoodIsSaved() {
        FoodRequestDto dto = buildDto();
        Food food = buildFood();
        when(foodMapper.toEntity(dto)).thenReturn(food);
        when(foodRepository.save(food)).thenReturn(food);

        Food result = foodService.create(dto);

        assertThat(result).isEqualTo(food);
        verify(foodMapper).toEntity(dto);
        verify(foodRepository).save(food);
    }

    @Test
    void getById_whenFoodExists_thenReturnFood() {
        Food food = buildFood();
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        Food result = foodService.getById(1L);

        assertThat(result).isEqualTo(food);
    }

    @Test
    void getById_whenFoodNotFound_thenThrowEntityNotFoundException() {
        when(foodRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> foodService.getById(99L));
    }

    @Test
    void update_whenFoodExists_thenMapperIsCalledAndFoodIsSaved() {
        FoodRequestDto dto = buildDto();
        Food food = buildFood();
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
        when(foodRepository.save(food)).thenReturn(food);

        foodService.update(1L, dto);

        verify(foodMapper).updateEntityFromDto(dto, food);
        verify(foodRepository).save(food);
    }

    @Test
    void delete_whenCalled_thenRepositoryDeleteByIdIsCalled() {
        foodService.delete(1L);

        verify(foodRepository).deleteById(1L);
    }

    private FoodRequestDto buildDto() {
        return new FoodRequestDto.Builder()
                .name("Chicken")
                .protein(40)
                .fat(10)
                .carbohydrates(0)
                .build();
    }

    private Food buildFood() {
        return new Food.Builder()
                .id(1L)
                .name("Chicken")
                .protein(40)
                .fat(10)
                .carbohydrates(0)
                .build();
    }
}