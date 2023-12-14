package bt6.dto;

import bt6.entity.Order;
import bt6.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequestDto {
    private Long productId;
    private int quantity = 1;
}
