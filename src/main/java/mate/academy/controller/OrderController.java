package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.CreateOrderInfoDto;
import mate.academy.dto.OrderItemResponseDto;
import mate.academy.dto.OrderResponseDto;
import mate.academy.dto.UpdateOrderStatusRequestDto;
import mate.academy.service.OrderItemService;
import mate.academy.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Orders Management", description = "API for managing orders")

public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    @Operation(summary = "Create Order", description = "Add order to DB")
    public void createOrder(@RequestBody @Valid CreateOrderInfoDto createOrderInfoDto) {
        orderService.createOrder(createOrderInfoDto);
    }

    @GetMapping
    @Operation(summary = "Get all orders from current logged user",
            description = "Get all orders from DB")
    public List<OrderResponseDto> getOrderHistory() {
        return orderService.getOrderHistory();
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get all order items from current "
            + "logged user",
            description = "Get all orders items from DB")
    public List<OrderItemResponseDto> getOrderItem(@PathVariable Long orderId) {
        return orderItemService.getOrderItems(orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get a specific order item by order id and item id",
            description = "Get specific order from DB")
    public OrderItemResponseDto getOrderItem(@PathVariable Long orderId,
                                             @PathVariable Long itemId) {
        return orderItemService.getSpecificOrderItemFromOrder(orderId, itemId);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update status on the order as Admin",
            description = "Update status on the order as Admin in DB")
    public void updateOrderStatus(@PathVariable Long id,
                                  @RequestBody @Valid
                                  UpdateOrderStatusRequestDto
                                          updateOrderStatusRequestDto) {
        orderService.updateOrderStatus(id, updateOrderStatusRequestDto);
    }
}
