
package kegly.organisation.nutrition.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "targets")
@Getter
@Setter
@NoArgsConstructor
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer protein;

    @Column(nullable = false)
    private Integer fat;

    @Column(nullable = false)
    private Integer carbohydrates;

    @Column(nullable = false)
    private Integer ccal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private GoalType type;

    public static Target of(GoalType type, int ccal, int protein, int fat) {
        int carbs = (ccal - protein * 4 - fat * 9) / 4;
        Target t = new Target();
        t.type = type;
        t.ccal = ccal;
        t.protein = protein;
        t.fat = fat;
        t.carbohydrates = carbs;
        return t;
    }
}