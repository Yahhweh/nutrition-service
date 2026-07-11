package kegly.organisation.nutrition.entity;

public enum GoalType {

    DIRTY_BULK    ( 0.0065, 1.8, 1.0),
    LEAN_BULK     ( 0.0035, 2.0, 0.9),
    RECOMPOSITION ( 0.0,    2.2, 0.9),
    CUTTING       (-0.0075, 2.4, 0.8);

    private final double weeklyBodyweightFraction;
    private final double proteinPerKg;
    private final double fatPerKg;

    GoalType(double weeklyBodyweightFraction, double proteinPerKg, double fatPerKg) {
        this.weeklyBodyweightFraction = weeklyBodyweightFraction;
        this.proteinPerKg = proteinPerKg;
        this.fatPerKg = fatPerKg;
    }

    public double weeklyChangeKg(double currentWeightKg) {
        return currentWeightKg * weeklyBodyweightFraction;
    }

    public double proteinGrams(double currentWeightKg) {
        return proteinPerKg * currentWeightKg;
    }

    public double fatGrams(double currentWeightKg) {
        return fatPerKg * currentWeightKg;
    }
}