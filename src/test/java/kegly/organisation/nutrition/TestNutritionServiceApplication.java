package kegly.organisation.nutrition;

import org.springframework.boot.SpringApplication;

public class TestNutritionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(NutritionServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
