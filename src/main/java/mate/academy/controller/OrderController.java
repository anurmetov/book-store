package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDtoWithoutCategoryIds;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;
import mate.academy.dto.CreateOrderInfoDto;
import mate.academy.service.BookService;
import mate.academy.service.CategoryService;
import mate.academy.service.OrderItemService;
import mate.academy.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Category Management", description = "API for managing categories")

public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Get all categories", description = "Get all categories from DB")
    public void createOrder(@RequestBody @Valid CreateOrderInfoDto createOrderInfoDto) {
        orderService.createOrder(createOrderInfoDto);
    }

}
