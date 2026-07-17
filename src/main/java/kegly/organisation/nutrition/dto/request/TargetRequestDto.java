package kegly.organisation.nutrition.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kegly.organisation.nutrition.entity.GoalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetRequestDto {

    @Schema(description = "ID of the user", example = "1")
    @NotNull
    private Long userId;

    @Schema(description = "Goal type: DIRTY_BULK, LEAN_BULK, RECOMPOSITION, CUTTING", example = "CUTTING")
    @NotNull
    private GoalType type;
}