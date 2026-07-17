package kegly.organisation.nutrition.entity.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static kegly.organisation.nutrition.domain.constants.CcalConstants.*;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Nutrition {
    @Column(nullable = false)
    private Integer protein;
    @Column(nullable = false)
    private Integer fat;
    @Column(nullable = false)
    private Integer carbohydrates;
    @Column(nullable = false)
    private Integer ccal;

    public Nutrition(Integer protein, Integer fat, Integer carbohydrates) {
        if (protein == null || fat == null || carbohydrates == null)
            throw new IllegalArgumentException("nutrients must not be null");
        if (protein < 0 || fat < 0 || carbohydrates < 0)
            throw new IllegalArgumentException("nutrients must not be negative");
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.ccal = protein * PROTEIN_COEFFICIENT
                + fat * FAT_COEFFICIENT
                + carbohydrates * CARBOHYDRATES_COEFFICIENT;
    }
    public Nutrition scaledBy(int grams) {
        return new Nutrition(
                protein * grams / 100,
                fat * grams / 100,
                carbohydrates * grams / 100
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nutrition n)) return false;
        return protein.equals(n.protein) && fat.equals(n.fat)
                && carbohydrates.equals(n.carbohydrates);
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(protein, fat, carbohydrates);
    }
}
