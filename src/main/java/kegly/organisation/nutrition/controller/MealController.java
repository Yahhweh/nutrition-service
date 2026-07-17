package kegly.organisation.nutrition.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kegly.organisation.nutrition.dto.request.MealRequestDto;
import kegly.organisation.nutrition.dto.response.MealResponseDto;
import kegly.organisation.nutrition.entity.valueObject.Nutrition;
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
@Tag(
        name = "Meal", description = "CRUD for meals and per-portion nutrition calculation"
)
public class MealController {

    private final MealService mealService;
    private final MealMapper mealMapper;

    @Operation(summary = "Create new meal")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Meal created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or food not found", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MealResponseDto> create(@RequestBody @Valid MealRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mealMapper.toDto(mealService.add(dto)));
    }

    @Operation(summary = "Get meal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Meal found"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mealMapper.toDto(mealService.getById(id)));
    }

    @Operation(summary = "Get all meals by user id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of meals"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<MealResponseDto>> getByUserId(@RequestParam Long userId) {
        List<MealResponseDto> meals = mealService.findByUserId(userId).stream()
                .map(mealMapper::toDto)
                .toList();
        return ResponseEntity.ok(meals);
    }

    @Operation(summary = "Calculate nutrition for a meal by portion size")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nutrition calculated"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content)
    })
    @GetMapping("/nutrition")
    public ResponseEntity<Nutrition> getMealNutrition(@RequestParam Long mealId){
        return ResponseEntity.ok(mealService.getNutritionByGrams(mealId));
    }

    @Operation(summary = "Delete meal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Meal deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }
}