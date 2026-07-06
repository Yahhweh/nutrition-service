package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.request.FoodRequestDto;
import kegly.organisation.nutrition.dto.response.FoodResponseDto;
import kegly.organisation.nutrition.entity.Food;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FoodMapper {

    FoodResponseDto toDto(Food food);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ccal", ignore = true)
    Food toEntity(FoodRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ccal", ignore = true)
    void updateEntityFromDto(FoodRequestDto dto, @MappingTarget Food food);
}