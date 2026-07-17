package kegly.organisation.nutrition.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.dto.response.TargetResponseDto;
import kegly.organisation.nutrition.dto.update.TargetUpdateDto;
import kegly.organisation.nutrition.mapper.TargetMapper;
import kegly.organisation.nutrition.service.TargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/targets")
@RequiredArgsConstructor
@Tag(name = "Target", description = "CRUD operations for nutrition targets")
public class TargetController {

    private final TargetService targetService;
    private final TargetMapper targetMapper;

    @Operation(summary = "Create new target")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Target created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TargetResponseDto> create(@RequestBody @Valid TargetRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(targetMapper.toDto(targetService.create(dto)));
    }

    @Operation(summary = "Get target by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Target found"),
            @ApiResponse(responseCode = "404", description = "Target not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TargetResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(targetMapper.toDto(targetService.getById(id)));
    }

    @Operation(summary = "Update target by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Target updated successfully"),
            @ApiResponse(responseCode = "404", description = "Target not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TargetResponseDto> update(@PathVariable Long id,
                                                    @RequestBody @Valid TargetUpdateDto dto) {
        return ResponseEntity.ok(targetMapper.toDto(targetService.update(id, dto)));
    }

    @Operation(summary = "Delete target by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Target deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Target not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        targetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}