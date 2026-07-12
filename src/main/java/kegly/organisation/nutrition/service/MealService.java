package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.MealRequestDto;
import kegly.organisation.nutrition.entity.Food;
import kegly.organisation.nutrition.entity.Meal;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.repository.FoodRepository;
import kegly.organisation.nutrition.repository.MealRepository;
import kegly.organisation.nutrition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public Meal add(MealRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found: id=" + dto.getUserId()));
        Food food = foodRepository.findById(dto.getFoodId())
                .orElseThrow(() -> new EntityNotFoundException("Food not found: id=" + dto.getFoodId()));
        Meal meal = new Meal();
        meal.setUser(user);
        meal.setFood(food);
        return mealRepository.save(meal);
    }

    @Transactional(readOnly = true)
    public Meal getById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<Meal> findByUserId(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    @Transactional
    public void delete(Long id) {
        mealRepository.deleteById(id);
    }
}