
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
    private Double protein;

    @Column(nullable = false)
    private Double fat;

    @Column(nullable = false)
    private Double carbohydrates;

    @Column(nullable = false)
    private Integer ccal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private GoalType type;
}