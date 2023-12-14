package bt6.service.impl.user;

import bt6.dto.UserResponse;
import bt6.dto.UserSignupDto;
import bt6.entity.User;
import bt6.exception.ConfirmException;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;
import bt6.repository.UserRepository;
import bt6.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) throws NotFoundException {
        User user = userRepository.findById(id).get();
        if (user != null){
            return userMapper.toResponse(user);
        }
        throw new NotFoundException("Không tìm thấy user");
    }

    @Override
    public UserResponse save(UserSignupDto userSignupDto) throws ExistException,ConfirmException{
        if (userRepository.existsByUsername(userSignupDto.getUsername())){
            throw new ExistException(userSignupDto.getUsername() + " đã tồn tại");
        }if (userRepository.existsByEmail(userSignupDto.getEmail())){
            throw new ExistException(userSignupDto.getEmail() + " đã tồn tại");
        }if (!userSignupDto.getPassword().equals(userSignupDto.getConfirmPassword())){
            throw new ConfirmException("Xác nhận mật khẩu không đúng");
        }
        User newUser = userMapper.toEntity(userSignupDto);
        newUser.setStatus(true);
        return userMapper.toResponse(userRepository.save(newUser));
    }
}
