package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.domain.NutritionCalculator;
import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.dto.update.TargetUpdateDto;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.mapper.TargetMapper;
import kegly.organisation.nutrition.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TargetService {

    private final TargetRepository targetRepository;
    private final NutritionCalculator nutritionCalculator;
    private final TargetMapper targetMapper;
    private final UserService userService;

    @Transactional
    public Target create(TargetRequestDto dto) {
        Target target = nutritionCalculator.calculateNutritionCalculator(dto);
        Target saved = targetRepository.save(target);
        userService.findByTelegramId(dto.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        return saved;
    }

    @Transactional(readOnly = true)
    public Target getById(Long id) {
        return targetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Target not found: id=" + id));
    }

    @Transactional
    public Target update(Long id, TargetUpdateDto dto) {
        Target target = getById(id);
        targetMapper.updateEntityFromDto(dto, target);
        return targetRepository.save(target);
    }

    @Transactional
    public void delete(Long id) {
        targetRepository.deleteById(id);
    }
}