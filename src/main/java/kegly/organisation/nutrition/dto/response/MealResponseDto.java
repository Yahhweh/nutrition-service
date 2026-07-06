package kegly.organisation.nutrition.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealResponseDto {

    private Long id;
    private Long userId;
    private Long foodId;
    private Instant createdAt;
}