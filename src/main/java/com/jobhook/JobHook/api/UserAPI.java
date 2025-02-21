package com.jobhook.JobHook.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobhook.JobHook.dto.LoginDTO;
import com.jobhook.JobHook.dto.ResponseDTO;
import com.jobhook.JobHook.dto.UserDTO;
import com.jobhook.JobHook.exception.JobPortalException;
import com.jobhook.JobHook.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException{
        userDTO=userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException{
        UserDTO userDTO=userService.loginUser(loginDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO> sendOtp(@PathVariable String email) throws Exception{
        userService.sendOTP(email);
        return new ResponseEntity<>(new ResponseDTO("Otp Sent Successfully"),HttpStatus.OK);
    }
    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable@Email(message = "{user.email.invalid}") String email,@PathVariable @Pattern(regexp = "^[0-9]{6}$",message = "{otp.invalid}") String otp) throws JobPortalException{
        userService.verifyOtp(email, otp);
        return new ResponseEntity<>(new ResponseDTO("Otp Verified Successfully"),HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException{
        ResponseDTO responseDTO= userService.changePassword(loginDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws JobPortalException{
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);

    }
}
