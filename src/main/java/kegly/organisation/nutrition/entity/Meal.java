package kegly.organisation.nutrition.entity;

import jakarta.persistence.*;
import kegly.organisation.nutrition.entity.valueObject.Nutrition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor
public class Meal {

    private final static Integer DEFAULT_GRAMS = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "grams", nullable = false, updatable = false)
    private Integer grams;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public Nutrition getNutritionByGrams(){
        Integer protein = food.getNutrition().getProtein() * grams / DEFAULT_GRAMS;
        Integer carbohydrates = food.getNutrition().getCarbohydrates() * grams / DEFAULT_GRAMS;
        Integer fat = food.getNutrition().getFat() * grams / DEFAULT_GRAMS;
        Integer ccal = food.getNutrition().getCcal() * grams / DEFAULT_GRAMS;

        Nutrition nutrition = new Nutrition();
        //TODO make builder for Nutrition
        nutrition.setProtein(protein);
        nutrition.setCcal(ccal);
        nutrition.setCarbohydrates(carbohydrates);
        nutrition.setFat(fat);
        return nutrition;
    }
}