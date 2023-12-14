package bt6.service.mapper;

import bt6.dto.ProductRequestDto;
import bt6.dto.ProductResponseDto;
import bt6.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IGenericMapper<Product,ProductRequestDto, ProductResponseDto> {


    @Override
    public Product toEntity(ProductRequestDto productRequestDto) {
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .stock(productRequestDto.getStock()).build();
    }

    @Override
    public ProductResponseDto toResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .ProductName(product.getProductName())
                .image(product.getImage())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
