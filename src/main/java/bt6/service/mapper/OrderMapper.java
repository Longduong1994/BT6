package bt6.service.mapper;

import bt6.dto.OrderRequestDto;
import bt6.dto.OrderResponse;
import bt6.entity.Order;
import bt6.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderMapper implements IGenericMapper<Order, OrderRequestDto, OrderResponse>{
    @Override
    public Order toEntity(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .addressShip(orderRequestDto.getAddressShip())
                .phone(orderRequestDto.getPhone())
                .note(orderRequestDto.getNote())
                .status(OrderStatus.PENDING)
                .createdAtt(new Date()).build();
    }

    @Override
    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .buyer(order.getUser().getUsername())
                .address(order.getAddressShip())
                .phone(order.getPhone())
                .note(order.getNote())
                .status(order.getStatus().name()).build();
    }
}
