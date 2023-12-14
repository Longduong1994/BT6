package bt6.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotNull(message = "File cannot be null")
    private MultipartFile multipartFile;

    @Min(value = 1, message = "Price must be at least 1")
    private double price;

    @Min(value = 1, message = "Stock must be at least 1")
    private Long stock;
}
