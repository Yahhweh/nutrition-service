package kegly.organisation.nutrition.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static kegly.organisation.nutrition.domain.constants.BMRConstants.*;
import static kegly.organisation.nutrition.domain.constants.BMRConstants.AGE_CONSTANT;
import static kegly.organisation.nutrition.domain.constants.BMRConstants.FEMALE_CONSTANT;
import static kegly.organisation.nutrition.domain.constants.BMRConstants.HEIGHT_CONSTANT;
import static kegly.organisation.nutrition.domain.constants.BMRConstants.MALE_CONSTANT;
import static kegly.organisation.nutrition.domain.constants.BMRConstants.WEIGHT_CONSTANT;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id", nullable = false, unique = true)
    private Long telegramId;

    private String username;

    private Double weight;

    @Column(nullable = false)
    private Integer height;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Activity activity;

    public Integer calculateTDEE(){
        return (int) (calculateBMR() * activity.getActivityMultiplier());
    }

    public Integer calculateBMR(){
        if(sex.equals(Sex.MALE)){
            return calculateMaleBMR();
        }
        return calculateFemaleBMR();
    }

    private Integer calculateMaleBMR(){
        return (int) (WEIGHT_CONSTANT * weight + HEIGHT_CONSTANT* height
                        - AGE_CONSTANT * age + MALE_CONSTANT);
    }

    private Integer calculateFemaleBMR(){
        return (int) (WEIGHT_CONSTANT * weight + HEIGHT_CONSTANT* height
                        - AGE_CONSTANT * age - FEMALE_CONSTANT);
    }
}