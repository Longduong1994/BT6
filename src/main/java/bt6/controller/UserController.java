package bt6.controller;

import bt6.dto.UserResponse;
import bt6.dto.UserSignupDto;
import bt6.exception.ConfirmException;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;
import bt6.service.impl.user.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserSignupDto userSignupDto) throws ConfirmException, ExistException {
        return new ResponseEntity<>(userService.save(userSignupDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserResponse> findById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
