package kegly.organisation.nutrition.controller;

import jakarta.validation.Valid;
import kegly.organisation.nutrition.dto.request.FoodRequestDto;
import kegly.organisation.nutrition.dto.response.FoodResponseDto;
import kegly.organisation.nutrition.mapper.FoodMapper;
import kegly.organisation.nutrition.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final FoodMapper foodMapper;

    @PostMapping
    public ResponseEntity<FoodResponseDto> create(@RequestBody @Valid FoodRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodMapper.toDto(foodService.create(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(foodMapper.toDto(foodService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDto> update(@PathVariable Long id,
                                                  @RequestBody @Valid FoodRequestDto dto) {
        return ResponseEntity.ok(foodMapper.toDto(foodService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}