package kegly.organisation.nutrition.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Food", description = "CRUD operations for food")
public class FoodController {

    private final FoodService foodService;
    private final FoodMapper foodMapper;

    @Operation(summary = "Create new food")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Food created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content)
    })
    @PostMapping
    public ResponseEntity<FoodResponseDto> create(@RequestBody @Valid FoodRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodMapper.toDto(foodService.create(dto)));
    }

    @Operation(summary = "Get food by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Food found"),
            @ApiResponse(responseCode = "404", description = "Food not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(foodMapper.toDto(foodService.getById(id)));
    }

    @Operation(summary = "Update food by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Food updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "404", description = "Food not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDto> update(@PathVariable Long id,
                                                  @RequestBody @Valid FoodRequestDto dto) {
        return ResponseEntity.ok(foodMapper.toDto(foodService.update(id, dto)));
    }

    @Operation(summary = "Delete food by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Food deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Food not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}