package mate.academy.mapper;

import mate.academy.config.MapperConfig;
import mate.academy.dto.OrderItemResponseDto;
import mate.academy.dto.OrderResponseDto;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toResponseDto(Order order);

    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto itemToResponseDto(OrderItem orderItem);
}
