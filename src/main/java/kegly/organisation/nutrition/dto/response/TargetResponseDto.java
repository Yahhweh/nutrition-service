package kegly.organisation.nutrition.dto.response;

import kegly.organisation.nutrition.entity.GoalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetResponseDto {

    private Long id;
    private Double protein;
    private Double fat;
    private Double carbohydrates;
    private Integer ccal;
    private GoalType type;
}