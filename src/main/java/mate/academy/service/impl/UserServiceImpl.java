package mate.academy.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.exception.RegistrationProcessingException;
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
        Optional<User> userFromDb = userRepository
                .findUserByEmail(userRequestRegistrationDto.email());
        if (userFromDb.isPresent()) {
            throw new RegistrationProcessingException("User with email: "
                    + userRequestRegistrationDto.email() + " exists.") ;
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
