package kegly.organisation.nutrition.dto.update;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Integer protein;
    private Integer fat;
    private Integer carbohydrates;
    private Integer ccal;
    private GoalType type;
}
