package kegly.organisation.nutrition.controller;

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
public class TargetController {

    private final TargetService targetService;
    private final TargetMapper targetMapper;

    @PostMapping
    public ResponseEntity<TargetResponseDto> create(@RequestBody @Valid TargetRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(targetMapper.toDto(targetService.create(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TargetResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(targetMapper.toDto(targetService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TargetResponseDto> update(@PathVariable Long id,
                                                    @RequestBody @Valid TargetUpdateDto dto) {
        return ResponseEntity.ok(targetMapper.toDto(targetService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        targetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}