package bt6.controller;

import bt6.dto.ProductRequestDto;
import bt6.dto.ProductResponseDto;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;
import bt6.service.impl.product.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "") String name,
                                     @RequestParam(defaultValue = "0" ) int page,
                                     @RequestParam(defaultValue = "5") int size){
        return new ResponseEntity<>(productService.findAll(name, page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@ModelAttribute @Valid ProductRequestDto productRequestDto) throws ExistException {
        return new ResponseEntity<>(productService.save(productRequestDto), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> editProduct(@ModelAttribute @Valid ProductRequestDto productRequestDto,@PathVariable("id") Long id) throws ExistException {
        return new ResponseEntity<>(productService.update(productRequestDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)throws NotFoundException {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

}
