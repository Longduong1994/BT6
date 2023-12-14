package bt6.dto;

import bt6.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    @NotNull(message="User không được để trống")
    private Long userId;
    @NotEmpty(message="Địa chỉ không được để trống")
    private String addressShip;
    @Pattern(regexp ="\\b\\d{10,11}\\b" ,message = "Số điện thoại không đúng định dạng")
    private String phone;
    private String note;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

}
