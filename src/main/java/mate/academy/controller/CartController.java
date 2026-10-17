package mate.academy.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mate.academy.dto.CreateCartItemRequestDto;
import mate.academy.dto.UpdateCartItemDto;
import mate.academy.model.ShoppingCart;
import mate.academy.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
@Tag(name = "Cart Management", description = "API for managing carts")
public class CartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addBook(@RequestBody
                                            @Valid CreateCartItemRequestDto
                                                    createCartItemRequestDto) {
        shoppingCartService.addBook(createCartItemRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ShoppingCart getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PutMapping("/items/{cartItemId}")
    public void updateCartItem(@PathVariable Long cartItemId,
                               @RequestBody @Valid UpdateCartItemDto updateCartItemDto) {
        shoppingCartService.updateBookQuantity(cartItemId, updateCartItemDto);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        shoppingCartService.removeBook(cartItemId);
    }
}
