package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Shopping Cart and Items Management",
        description = "API for managing shopping carts and their items")
public class CartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add book to the shopping cart")
    public ResponseEntity<Void> addBook(@RequestBody
                                            @Valid CreateCartItemRequestDto
                                                    createCartItemRequestDto) {
        shoppingCartService.addBook(createCartItemRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get shopping cart from the current logged user")
    @GetMapping
    public ShoppingCart getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PutMapping("/items/{cartItemId}")
    @Operation(summary = "Update a quantity of the item (book) in the shopping cart")
    public void updateCartItem(@PathVariable Long cartItemId,
                               @RequestBody @Valid UpdateCartItemDto updateCartItemDto) {
        shoppingCartService.updateBookQuantity(cartItemId, updateCartItemDto);
    }

    @DeleteMapping("/items/{cartItemId}")
    @Operation(summary = "Delete the item (book) from the shopping cart")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        shoppingCartService.removeBook(cartItemId);
    }
}
