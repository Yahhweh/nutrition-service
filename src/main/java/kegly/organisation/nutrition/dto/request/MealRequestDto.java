package kegly.organisation.nutrition.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MealRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long foodId;

    @NotNull
    private Integer grams;

    MealRequestDto(Builder builder) {
        this.userId = builder.userId;
        this.foodId = builder.foodId;
        this.grams = builder.grams;
    }

    public static class Builder {
        private Long userId;
        private Long foodId;
        private Integer grams;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder grams(Integer grams){
            this.grams = grams;
            return this;
        }

        public Builder foodId(Long foodId) {
            this.foodId = foodId;
            return this;
        }

        public MealRequestDto build() {
            return new MealRequestDto(this);
        }
    }
}