package kegly.organisation.nutrition.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    private Long telegramId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Positive
    private Double weight;
}