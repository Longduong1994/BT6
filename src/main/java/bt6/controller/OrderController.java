package bt6.controller;

import bt6.dto.*;
import bt6.entity.CartItem;
import bt6.exception.NotFoundException;
import bt6.service.impl.cart.ICartItemService;
import bt6.service.impl.order.IOrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    private final ICartItemService cartItemService;


    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) throws NotFoundException {
        return new ResponseEntity<>(orderService.save(orderRequestDto), HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<CartItemResponse> addToCart(@RequestBody @Valid CartItemRequestDto cartItemRequestDto){
        return new ResponseEntity<>(cartItemService.addToCart(cartItemRequestDto),HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Page<CartItemResponse>> getCartItems(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(cartItemService.findAll(page, size),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<OrderDetail> getDetail(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(cartItemService.findOrderDetail(id), HttpStatus.OK);
    }


}
