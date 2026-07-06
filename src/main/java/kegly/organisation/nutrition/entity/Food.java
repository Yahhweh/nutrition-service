package kegly.organisation.nutrition.entity;

import jakarta.persistence.*;
import kegly.organisation.nutrition.constants.Default;
import lombok.Getter;
import lombok.Setter;

import static kegly.organisation.nutrition.constants.CcalConstants.*;

@Entity
@Table(name = "food")
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer protein;

    @Column(nullable = false)
    private Integer fat;

    @Column(nullable = false)
    private Integer carbohydrates;

    @Column(nullable = false)
    private Integer ccal;

    @Default
    public Food(String name, Integer protein, Integer fat, Integer carbohydrates) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        computeCcal();
    }

    public Food(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.protein = builder.protein;
        this.fat = builder.fat;
        this.carbohydrates = builder.carbohydrates;
        computeCcal();
    }

    public Food() {
    }

    @PrePersist
    @PreUpdate
    void computeCcal() {
        if (protein != null && fat != null && carbohydrates != null) {
            ccal = protein * PROTEIN_COEFFICIENT + fat * FAT_COEFFICIENT + carbohydrates * CARBOHYDRATES_COEFFICIENT;
        }
    }

    public static class Builder {
        private Long id;
        private String name;
        private Integer protein;
        private Integer fat;
        private Integer carbohydrates;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public Food build() {
            return new Food(this);
        }
    }
}