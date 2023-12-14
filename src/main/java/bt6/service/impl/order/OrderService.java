package bt6.service.impl.order;

import bt6.dto.OrderRequestDto;
import bt6.dto.OrderResponse;
import bt6.entity.*;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;
import bt6.repository.CartItemRepository;
import bt6.repository.OrderRepository;
import bt6.repository.UserRepository;
import bt6.service.impl.cart.ICartItemService;
import bt6.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public Order findById(Long id) throws NotFoundException {
        Order order = orderRepository.findById(id).get();
        if (order != null){
            return order;
        }
        throw new NotFoundException("Không tìm thấy order");
    }

    @Override
    public OrderResponse save(OrderRequestDto orderRequest) throws NotFoundException {
        User user = userRepository.findById(orderRequest.getUserId()).get();
        if (user == null) {
            throw new NotFoundException("Không tìm thấy user");
        }
       Order order = orderMapper.toEntity(orderRequest);
        order.setUser(user);
        orderRepository.save(order);
        for (CartItem c: cartItemRepository.findAllByStatusIsTrue()) {
            c.setOrder(order);
            c.setStatus(false);
            cartItemRepository.save(c);
        }
        return orderMapper.toResponse(order);
    }
}
