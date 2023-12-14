package bt6.service.impl.cart;

import bt6.dto.CartItemRequestDto;
import bt6.dto.CartItemResponse;
import bt6.entity.CartItem;
import bt6.entity.Order;
import bt6.entity.Product;
import bt6.exception.NotFoundException;
import bt6.repository.CartItemRepository;
import bt6.repository.ProductRepository;
import bt6.service.impl.order.IOrderService;
import bt6.service.mapper.CartItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final IOrderService orderService;
    private final ProductRepository productRepository;

    @Override
    public Page<CartItemResponse> findAll(int page, int size) {
        return cartItemRepository.findAllByStatusIsTrue(PageRequest.of(page, size)).map(cartItemMapper::toResponse);
    }

    @Override
    public List<CartItemResponse> findByOrder(Long id) throws NotFoundException {
        Order order = orderService.findById(id);
        return cartItemRepository.findAllByOrder(order).stream().map(cartItemMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public CartItemResponse findById(Long id) throws NotFoundException {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if (cartItem != null) return cartItemMapper.toResponse(cartItem);
        throw new NotFoundException("Không tìm thấy sản phẩm");
    }

    @Override
    public CartItemResponse addToCart(CartItemRequestDto cartItemDto) {
        Product product = productRepository.findById(cartItemDto.getProductId()).get();
        CartItem cartItem = cartItemRepository.findByProduct(product);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        } else {
            cartItem = cartItemMapper.toEntity(cartItemDto);
            cartItem.setProduct(product);
            cartItem.setStatus(true);
        }
        return cartItemMapper.toResponse(cartItemRepository.save(cartItem));
    }


    @Override
    public CartItemResponse updateCart(CartItemRequestDto cartItemRequestDto, Long id) throws NotFoundException {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if (cartItem == null) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }
        cartItem.setQuantity(cartItemRequestDto.getQuantity());
        return cartItemMapper.toResponse(cartItemRepository.save(cartItem));
    }

    @Override
    public String delete(Long id) throws NotFoundException {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if (cartItem != null){
            cartItemRepository.delete(cartItem);
            return "Xóa thành công";
        }
        throw new NotFoundException("Không tìm sản phẩm ");
    }
}
