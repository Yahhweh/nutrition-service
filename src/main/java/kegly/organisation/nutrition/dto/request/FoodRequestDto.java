package kegly.organisation.nutrition.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodRequestDto {

    @Schema(description = "Food name", example = "Chicken breast", minLength = 2, maxLength = 100)
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Schema(description = "Protein per 100g, must be >= 0", example = "31")
    @NotNull
    @Min(0)
    private Integer protein;

    @Schema(description = "Fat per 100g, must be >= 0", example = "3")
    @NotNull
    @Min(0)
    private Integer fat;

    @Schema(description = "Carbohydrates per 100g, must be >= 0", example = "0")
    @NotNull
    @Min(0)
    private Integer carbohydrates;

    FoodRequestDto(Builder builder) {
        this.name = builder.name;
        this.protein = builder.protein;
        this.fat = builder.fat;
        this.carbohydrates = builder.carbohydrates;
    }

    public static class Builder {
        private String name;
        private Integer protein;
        private Integer fat;
        private Integer carbohydrates;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder protein(Integer protein) {
            this.protein = protein;
            return this;
        }

        public Builder fat(Integer fat) {
            this.fat = fat;
            return this;
        }

        public Builder carbohydrates(Integer carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public FoodRequestDto build() {
            return new FoodRequestDto(this);
        }
    }
}