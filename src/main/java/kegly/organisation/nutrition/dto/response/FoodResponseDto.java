package kegly.organisation.nutrition.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDto {

    private Long id;
    private String name;
    private Integer protein;
    private Integer fat;
    private Integer carbohydrates;
    private Integer ccal;
}