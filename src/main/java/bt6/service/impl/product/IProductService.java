package bt6.service.impl.product;

import bt6.dto.ProductRequestDto;
import bt6.dto.ProductResponseDto;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface IProductService {

    Page<ProductResponseDto> findAll(String name, int page, int size);

    ProductResponseDto findById(Long id) throws NotFoundException;

    ProductResponseDto save(ProductRequestDto productRequestDto) throws ExistException;
    ProductResponseDto update(ProductRequestDto productRequestDto,Long id) throws ExistException;

    String delete(Long id) throws NotFoundException;
}
