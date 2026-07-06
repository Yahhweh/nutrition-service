package kegly.organisation.nutrition.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
    @DecimalMin("0.0")
    private Double protein;

    @NotNull
    @DecimalMin("0.0")
    private Double fat;

    @NotNull
    @DecimalMin("0.0")
    private Double carbohydrates;

    @NotNull
    @Min(0)
    private Integer ccal;

    @NotNull
    private GoalType type;
}