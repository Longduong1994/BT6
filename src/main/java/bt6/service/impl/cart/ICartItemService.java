package bt6.service.impl.cart;

import bt6.dto.CartItemRequestDto;
import bt6.dto.CartItemResponse;
import bt6.dto.OrderDetail;
import bt6.exception.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICartItemService {
    Page<CartItemResponse> findAll(int page, int size);

    List<CartItemResponse> findByOrder(Long id) throws NotFoundException;

    OrderDetail findOrderDetail(Long id) throws NotFoundException;

    CartItemResponse findById(Long id) throws NotFoundException;

    CartItemResponse addToCart(CartItemRequestDto cartItemDto);

    CartItemResponse updateCart(CartItemRequestDto cartItem,Long id) throws NotFoundException;


    String delete(Long id) throws NotFoundException;
}
