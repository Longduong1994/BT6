package bt6.service.mapper;

import bt6.dto.CartItemRequestDto;
import bt6.dto.CartItemResponse;
import bt6.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper implements IGenericMapper<CartItem, CartItemRequestDto, CartItemResponse>{
    @Override
    public CartItem toEntity(CartItemRequestDto cartItemDto) {
        return CartItem.builder()
                .quantity(cartItemDto.getQuantity()).build();
    }

    @Override
    public CartItemResponse toResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productName(cartItem.getProduct().getProductName())
                .image(cartItem.getProduct().getImage())
                .price(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .status(cartItem.isStatus()).build();
    }
}
