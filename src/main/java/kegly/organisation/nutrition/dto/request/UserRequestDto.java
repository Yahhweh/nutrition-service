package kegly.organisation.nutrition.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kegly.organisation.nutrition.entity.Activity;
import kegly.organisation.nutrition.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Schema(description = "Telegram user ID", example = "123456789")
    @NotNull
    private Long telegramId;

    @Schema(description = "Username", example = "john_doe", minLength = 2, maxLength = 50)
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @Schema(description = "Body weight in kg, must be > 0", example = "80.0")
    @NotNull
    @Positive
    private Double weight;

    @Schema(description = "Height in cm, must be > 0", example = "180")
    @NotNull
    @Positive
    private Integer height;

    @Schema(description = "Age in years", example = "25")
    @NotNull
    private Integer age;

    @Schema(description = "Biological sex: MALE or FEMALE", example = "MALE")
    @NotNull
    private Sex sex;

    @Schema(description = "Activity level: SEDENTARY, LIGHTLY_ACTIVE, MODERATELY_ACTIVE, VERY_ACTIVE, EXTRA_ACTIVE", example = "MODERATELY_ACTIVE")
    @NotNull
    private Activity activity;
}