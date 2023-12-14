package bt6.service.impl.order;

import bt6.dto.OrderRequestDto;
import bt6.dto.OrderResponse;
import bt6.entity.Order;
import bt6.exception.NotFoundException;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> findAll();

    Order findById(Long id) throws NotFoundException;

    OrderResponse save(OrderRequestDto orderRequest) throws NotFoundException;

}
