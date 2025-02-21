package com.jobhook.JobHook.service;

import com.jobhook.JobHook.dto.LoginDTO;
import com.jobhook.JobHook.dto.ResponseDTO;
import com.jobhook.JobHook.dto.UserDTO;
import com.jobhook.JobHook.exception.JobPortalException;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;
    public Boolean sendOTP(String email) throws Exception;
    public Boolean verifyOtp(String email,String otp) throws JobPortalException;
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException;
    public UserDTO getUserById(Long id) throws JobPortalException;
}
