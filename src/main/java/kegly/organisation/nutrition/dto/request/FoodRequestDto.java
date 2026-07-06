package kegly.organisation.nutrition.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FoodRequestDto {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Min(0)
    private Integer protein;

    @NotNull
    @Min(0)
    private Integer fat;

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