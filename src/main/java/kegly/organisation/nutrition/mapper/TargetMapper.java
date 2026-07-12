package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.response.TargetResponseDto;
import kegly.organisation.nutrition.dto.update.TargetUpdateDto;
import kegly.organisation.nutrition.entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TargetMapper {

    TargetResponseDto toDto(Target target);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TargetUpdateDto dto, @MappingTarget Target target);
}
