package mate.academy.mapper;

import mate.academy.config.MapperConfig;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserResponseDto toUserResponseDto(User user);

    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequestRegistrationDto userRequestRegistrationDto);
}
