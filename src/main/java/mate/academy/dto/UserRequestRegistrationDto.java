package mate.academy.dto;

public record UserRequestRegistrationDto(String email,
                                         String password,
                                         String repeatPassword,
                                         String firstName,
                                         String lastName,
                                         String shippingAddress) {
}
