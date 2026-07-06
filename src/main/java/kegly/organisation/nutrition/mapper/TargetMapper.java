package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.dto.response.TargetResponseDto;
import kegly.organisation.nutrition.entity.Target;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TargetMapper {


    TargetResponseDto toDto(Target target);

    @Mapping(target = "id", ignore = true)
    Target toEntity(TargetRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TargetRequestDto dto, @MappingTarget Target target);
}