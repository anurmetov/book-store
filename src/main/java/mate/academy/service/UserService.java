package mate.academy.service;

import mate.academy.dto.UserRequestLoginDto;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRequestRegistrationDto userRequestRegistrationDto);

    String login(UserRequestLoginDto userRequestLoginDto);
}
