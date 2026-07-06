package kegly.organisation.nutrition.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private Long telegramId;
    private String username;
    private Double weight;
    private Long targetId;
}