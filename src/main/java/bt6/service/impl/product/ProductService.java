package bt6.service.impl.product;

import bt6.dto.ProductRequestDto;
import bt6.dto.ProductResponseDto;
import bt6.entity.Product;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;
import bt6.repository.ProductRepository;
import bt6.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    private final String pathUpload= "C:\\Users\\Admin\\OneDrive\\Máy tính\\BT6\\src\\main\\resources\\images\\";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public Page<ProductResponseDto> findAll(String name, int page, int size, String field, String by, Double minPrice, Double maxPrice) {
        Sort sort = Sort.by(field);

        if (by.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Page<Product>  products = productRepository.findAllByProductNameContainingAndPriceBetween(name,minPrice,maxPrice, PageRequest.of(page, size).withSort(sort));;
        return products.map(productMapper::toResponse);
    }



    @Override
    public ProductResponseDto findById(Long id) throws NotFoundException {
        Product product = productRepository.findById(id).get();
        if (product != null){
            return productMapper.toResponse(product);
        }
        throw new NotFoundException("Không tìm thấy sản phẩm");
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) throws ExistException{
        if (productRepository.existsByProductName(productRequestDto.getProductName())){
            throw new ExistException("Sản phẩm đã tồn tại");
        }
        Product newProduct = productMapper.toEntity(productRequestDto);
        newProduct.setImage(uploadFile(productRequestDto.getMultipartFile()));
        newProduct.setStatus(true);
        return productMapper.toResponse(productRepository.save(newProduct));
    }

    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, Long id) throws ExistException {
        if (productRepository.existsByProductName(productRequestDto.getProductName())){
            throw new ExistException("Sản phẩm đã tồn tại");
        }
        Product editProduct = productMapper.toEntity(productRequestDto);
        editProduct.setId(id);
        editProduct.setImage(uploadFile(productRequestDto.getMultipartFile()));
        editProduct.setStatus(true);
        return productMapper.toResponse(productRepository.save(editProduct));
    }

    @Override
    public String delete(Long id) throws NotFoundException {
        Product product = productRepository.findById(id).get();
        if (product != null){
            productRepository.delete(product);
            return "Xóa thành công";
        }
        throw new NotFoundException("Không tìm sản phẩm ");
    }

    private String uploadFile(MultipartFile multipartFile){
        File file = new File(pathUpload);
        if(!file.exists()) {
            file.mkdir();
        }
        String  urlImg = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(multipartFile.getBytes(), new File(pathUpload + urlImg));
        }catch (Exception e){
            throw new RuntimeException(e);

        }
        return urlImg;
    }
}
