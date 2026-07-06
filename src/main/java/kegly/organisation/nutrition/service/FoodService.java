package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.FoodRequestDto;
import kegly.organisation.nutrition.entity.Food;
import kegly.organisation.nutrition.mapper.FoodMapper;
import kegly.organisation.nutrition.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    @Transactional
    public Food create(FoodRequestDto dto) {
        Food food = foodMapper.toEntity(dto);
        return foodRepository.save(food);
    }

    @Transactional(readOnly = true)
    public Food getById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found: id=" + id));
    }

    @Transactional
    public Food update(Long id, FoodRequestDto dto) {
        Food food = getById(id);
        foodMapper.updateEntityFromDto(dto, food);
        return foodRepository.save(food);
    }

    @Transactional
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }
}