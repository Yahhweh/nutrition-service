package kegly.organisation.nutrition.entity;

import jakarta.persistence.*;
import kegly.organisation.nutrition.annotation.Default;
import kegly.organisation.nutrition.entity.valueObject.Nutrition;
import lombok.Getter;
import lombok.Setter;

import static kegly.organisation.nutrition.domain.constants.CcalConstants.*;

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

    @Embedded
    private Nutrition nutrition;

    @Default
    public Food(String name, Nutrition nutrition) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name required");
        this.name = name;
        this.nutrition = nutrition;
    }

    public Food() {
    }


}