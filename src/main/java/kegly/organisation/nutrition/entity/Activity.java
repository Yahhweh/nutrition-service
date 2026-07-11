package kegly.organisation.nutrition.entity;

import lombok.Getter;

@Getter
public enum Activity {
    SEDENTARY(1.20),
    LIGHTLY_ACTIVE(1.375),
    MODERATELY_ACTIVE(1.55),
    VERY_ACTIVE(1.725),
    EXTRA_ACTIVE(1.90);

    private final double activityMultiplier;

    Activity(double activityMultiplier) {
        this.activityMultiplier = activityMultiplier;
    }
}
