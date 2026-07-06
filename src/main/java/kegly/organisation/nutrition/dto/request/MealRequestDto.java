package kegly.organisation.nutrition.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MealRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long foodId;

    MealRequestDto(Builder builder) {
        this.userId = builder.userId;
        this.foodId = builder.foodId;
    }

    public static class Builder {
        private Long userId;
        private Long foodId;

        public Builder userId(Long userId) {
            this.userId = userId;
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