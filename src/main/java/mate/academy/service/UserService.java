package mate.academy.service;

import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestRegistrationDto userRequestRegistrationDto);
}
