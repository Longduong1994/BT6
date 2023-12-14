package bt6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupDto {
    @NotEmpty(message = "Username không được để trống")
    private String username;
    @NotEmpty(message = "Email không được để trống")
    @Pattern(regexp ="^[A-Za-z0-9+_.-]+@(.+)$",message = "Email không đúng định dạng")
    private String email;
    @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d).{8,}$",message = "Mật khẩu phải có 8 kí tự, ít nhất có 1 chữ hoa, một chữ thường và 1 số")
    private String password;
    private String confirmPassword;
}
