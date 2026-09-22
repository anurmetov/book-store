package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRequestLoginDto;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.dto.UserResponseLoginDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.exception.RegistrationException;
import mate.academy.mapper.UserMapper;
import mate.academy.model.Role;
import mate.academy.model.User;
import mate.academy.repository.RoleRepository;
import mate.academy.repository.UserRepository;
import mate.academy.security.JwtUtil;
import mate.academy.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

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
        Role userRole = roleRepository.findByRole(Role.RoleName.ROLE_USER).orElseThrow(
                () -> new EntityNotFoundException("Cant find a role name for user: "
                        + Role.RoleName.ROLE_USER));
        user.setRoles(Set.of(userRole));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseLoginDto login(UserRequestLoginDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(authentication.getName());
            return new UserResponseLoginDto(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}

