package mate.academy.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.exception.RegistrationException;
import mate.academy.mapper.UserMapper;
import mate.academy.model.User;
import mate.academy.repository.UserRepository;
import mate.academy.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRequestRegistrationDto userRequestRegistrationDto) {
        if (userRequestRegistrationDto == null) {
            throw new RegistrationException("Request body can not be null");
        }
        if (userRequestRegistrationDto.email().isEmpty()) {
            throw new RegistrationException("User email is empty");
        }
        Optional<User> userFromDb = userRepository
                .findUserByEmail(userRequestRegistrationDto.email());
        if (userFromDb.isPresent()) {
            throw new RegistrationException("User with email: "
                    + userRequestRegistrationDto.email() + " exists.");
        }
        if (userRequestRegistrationDto.firstName().isEmpty()) {
            throw new RegistrationException("User first name is empty");
        }

        if (userRequestRegistrationDto.password().isEmpty()) {
            throw new RegistrationException("User password is empty");
        }

        if (userRequestRegistrationDto.repeatPassword().isEmpty()) {
            throw new RegistrationException("User repeat password is empty");
        }

        if (userRequestRegistrationDto.lastName().isEmpty()) {
            throw new RegistrationException("User last name is empty");
        }

        User registredUser = new User();
        registredUser.setEmail(userRequestRegistrationDto.email());
        registredUser.setPassword(userRequestRegistrationDto.password());
        registredUser.setFirstName(userRequestRegistrationDto.firstName());
        registredUser.setLastName(userRequestRegistrationDto.lastName());
        registredUser.setShippingAddress(userRequestRegistrationDto.shippingAddress());
        return userMapper.toUserResponseDto(userRepository.save(registredUser));
    }
}
