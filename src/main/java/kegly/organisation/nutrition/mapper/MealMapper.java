package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.response.MealResponseDto;
import kegly.organisation.nutrition.entity.Meal;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MealMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "foodId", source = "food.id")
    MealResponseDto toDto(Meal meal);
}