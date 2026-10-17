package mate.academy.service;

import mate.academy.dto.CreateCartItemRequestDto;
import mate.academy.dto.UpdateCartItemDto;
import mate.academy.model.ShoppingCart;

public interface ShoppingCartService {

    void addBook(CreateCartItemRequestDto createCartItemRequestDto);

    ShoppingCart getShoppingCart();

    void updateBookQuantity(Long cartItemId, UpdateCartItemDto updateCartItemDto);

    void removeBook(Long cartItemId);

}
