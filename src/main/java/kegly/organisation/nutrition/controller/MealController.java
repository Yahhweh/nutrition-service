package kegly.organisation.nutrition.controller;

import jakarta.validation.Valid;
import kegly.organisation.nutrition.dto.request.MealRequestDto;
import kegly.organisation.nutrition.dto.response.MealResponseDto;
import kegly.organisation.nutrition.mapper.MealMapper;
import kegly.organisation.nutrition.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final MealMapper mealMapper;

    @PostMapping
    public ResponseEntity<MealResponseDto> add(@RequestBody @Valid MealRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mealMapper.toDto(mealService.add(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mealMapper.toDto(mealService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<MealResponseDto>> getByUserId(@RequestParam Long userId) {
        List<MealResponseDto> meals = mealService.findByUserId(userId).stream()
                .map(mealMapper::toDto)
                .toList();
        return ResponseEntity.ok(meals);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }
}