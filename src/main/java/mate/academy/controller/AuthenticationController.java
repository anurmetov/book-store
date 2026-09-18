package mate.academy.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.UserRequestLoginDto;
import mate.academy.dto.UserRequestRegistrationDto;
import mate.academy.dto.UserResponseDto;
import mate.academy.exception.RegistrationException;
import mate.academy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(
        name = "User Authentication",
        description = "Endpoints for authenticating and authorizing users in the application"
)
public class AuthenticationController {
    private final UserService userService;

    @Tag(
            name = "User Registration",
            description = "Endpoint for registration of user in the application"
    )
    @PostMapping("/registration")
    public ResponseEntity<UserResponseDto>
            register(@RequestBody @Valid UserRequestRegistrationDto request)
                throws RegistrationException {
        UserResponseDto responseDto = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @Tag(
            name = "User Login",
            description = "Endpoint for authentication of user in the application"
    )
    @PostMapping("/login")
    public String authenticate(@RequestBody @Valid UserRequestLoginDto userRequestLoginDto)
            throws RegistrationException {
        return userService.login(userRequestLoginDto);
    }
}
