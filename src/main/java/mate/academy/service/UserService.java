package mate.academy.service;

import mate.academy.dto.UserRequestLoginDto;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.dto.UserResponseLoginDto;

public interface UserService {
    UserResponseDto register(UserRequestRegistrationDto userRequestRegistrationDto);

    UserResponseLoginDto login(UserRequestLoginDto userRequestLoginDto);
}
