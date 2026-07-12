package kegly.organisation.nutrition.dto.request;

import jakarta.validation.constraints.NotNull;
import kegly.organisation.nutrition.entity.GoalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private GoalType type;
}