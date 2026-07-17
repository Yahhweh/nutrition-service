package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.request.FoodRequestDto;
import kegly.organisation.nutrition.dto.response.FoodResponseDto;
import kegly.organisation.nutrition.entity.Food;
import kegly.organisation.nutrition.entity.valueObject.Nutrition;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FoodMapper {

    @Mapping(source = "nutrition.protein", target = "protein")
    @Mapping(source = "nutrition.fat", target = "fat")
    @Mapping(source = "nutrition.carbohydrates", target = "carbohydrates")
    @Mapping(source = "nutrition.ccal", target = "ccal")
    FoodResponseDto toDto(Food food);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nutrition", expression = "java(new Nutrition(dto.getProtein(), dto.getFat(), dto.getCarbohydrates()))")
    Food toEntity(FoodRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nutrition", expression = "java(new Nutrition(dto.getProtein(), dto.getFat(), dto.getCarbohydrates()))")
    void updateEntityFromDto(FoodRequestDto dto, @MappingTarget Food food);
}