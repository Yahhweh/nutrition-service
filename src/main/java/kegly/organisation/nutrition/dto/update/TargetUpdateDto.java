package kegly.organisation.nutrition.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import kegly.organisation.nutrition.entity.GoalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TargetUpdateDto {

    @Schema(description = "Override protein target in grams (optional)", example = "180")
    private Integer protein;

    @Schema(description = "Override fat target in grams (optional)", example = "70")
    private Integer fat;

    @Schema(description = "Override carbohydrates target in grams (optional)", example = "200")
    private Integer carbohydrates;

    @Schema(description = "Override calorie target (optional)", example = "2200")
    private Integer ccal;

    @Schema(description = "Change goal type: DIRTY_BULK, LEAN_BULK, RECOMPOSITION, CUTTING (optional)", example = "LEAN_BULK")
    private GoalType type;
}
