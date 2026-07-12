package kegly.organisation.nutrition.mapper;

import kegly.organisation.nutrition.dto.request.UserRequestDto;
import kegly.organisation.nutrition.dto.response.UserResponseDto;
import kegly.organisation.nutrition.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User user);
}