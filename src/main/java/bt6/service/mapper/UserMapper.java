package bt6.service.mapper;

import bt6.dto.UserResponse;
import bt6.dto.UserSignupDto;
import bt6.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IGenericMapper<User, UserSignupDto, UserResponse>{
    @Override
    public User toEntity(UserSignupDto userSignupDto) {
        return User.builder()
                .username(userSignupDto.getUsername())
                .email(userSignupDto.getEmail())
                .password(userSignupDto.getPassword()).build();
    }

    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .status(user.isStatus()).build();
    }
}
