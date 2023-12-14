package bt6.service.impl.user;

import bt6.dto.UserResponse;
import bt6.dto.UserSignupDto;
import bt6.entity.User;
import bt6.exception.ConfirmException;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;

import java.util.List;

public interface IUserService {
    List<UserResponse> findAll();

    UserResponse findById(Long id) throws NotFoundException;
    UserResponse save(UserSignupDto userSignupDto) throws ExistException, ConfirmException;

}
