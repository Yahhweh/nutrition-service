package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.request.UserRequestDto;
import kegly.organisation.nutrition.dto.response.UserResponseDto;
import kegly.organisation.nutrition.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "targetId", source = "target.id")
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "target", ignore = true)
    User toEntity(UserRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "target", ignore = true)
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User user);
}