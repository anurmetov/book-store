package mate.academy.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.CreateCartItemRequestDto;
import mate.academy.dto.UpdateCartItemDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.model.CartItem;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.repository.BookRepository;
import mate.academy.repository.CartItemRepository;
import mate.academy.repository.ShoppingCartRepository;
import mate.academy.repository.UserRepository;
import mate.academy.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void addBook(CreateCartItemRequestDto createCartItemRequestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByEmail(auth.getName()).orElseThrow();

        ShoppingCart cart = shoppingCartRepository.findShoppingCartByUser_Id(currentUser.getId());

        CartItem cartItem = new CartItem();
        cartItem.setBook(bookRepository.findById(createCartItemRequestDto.bookId())
                .orElseThrow(() -> new EntityNotFoundException("The book with id: "
                        + createCartItemRequestDto.bookId()
                        + " does not exist!")));
        cartItem.setShoppingCart(cart);
        cartItem.setQuantity(createCartItemRequestDto.quantity());
        cartItemRepository.save(cartItem);
    }

    @Override
    public ShoppingCart getShoppingCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByEmail(auth.getName()).orElseThrow();
        return shoppingCartRepository.findShoppingCartByUser_Id(currentUser.getId());
    }

    @Override
    public void updateBookQuantity(Long cartItemId, UpdateCartItemDto updateCartItemDto) {
        CartItem currentCartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(()
                        -> new EntityNotFoundException("Cart Item was not found by id: "
                        + cartItemId));

        currentCartItem.setQuantity(updateCartItemDto.quantity());
        cartItemRepository.save(currentCartItem);
    }

    @Override
    public void removeBook(Long cartItemId) {
        CartItem currentCartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cart Item was not found by id: "
                                + cartItemId));

        cartItemRepository.deleteById(currentCartItem.getId());
    }
}
