package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRequestLoginDto;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.exception.RegistrationException;
import mate.academy.mapper.UserMapper;
import mate.academy.model.Role;
import mate.academy.model.User;
import mate.academy.repository.RoleRepository;
import mate.academy.repository.UserRepository;
import mate.academy.security.TokenUtil;
import mate.academy.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Role.RoleName USER_ROLE = Role.RoleName.ROLE_USER;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;

    @Override
    public UserResponseDto register(UserRequestRegistrationDto userRequestRegistrationDto) {
        if (userRepository.existsUserByEmail((userRequestRegistrationDto.email()))) {
            throw new RegistrationException(
                    String.format("User with this email: %s already exists",
                            userRequestRegistrationDto.email())
            );
        }
        User user = userMapper.toUser(userRequestRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRequestRegistrationDto.password()));
        user.setRoles(Set.of(roleRepository.findByRole(USER_ROLE).orElseThrow(
                () -> new EntityNotFoundException("Cant find a role name for user: " + USER_ROLE)
        )));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public String login(UserRequestLoginDto request) {
        Optional<User> user = userRepository.findUserByEmail(request.email());
        if (user.isEmpty()) {
            throw new RuntimeException("Can't login");
        }
        if (!passwordEncoder.matches(
                request.password(),
                user.get().getPassword())) {
            throw new RuntimeException("Can't login");
        }
        return tokenUtil.generateToken(request.email());
    }
}
