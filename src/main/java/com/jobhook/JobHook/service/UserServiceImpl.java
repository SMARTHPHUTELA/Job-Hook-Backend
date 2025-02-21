package com.jobhook.JobHook.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobhook.JobHook.dto.LoginDTO;
import com.jobhook.JobHook.dto.ResponseDTO;
import com.jobhook.JobHook.dto.UserDTO;
import com.jobhook.JobHook.entity.OTP;
import com.jobhook.JobHook.entity.User;
import com.jobhook.JobHook.exception.JobPortalException;
import com.jobhook.JobHook.repository.OtpRepository;
import com.jobhook.JobHook.repository.UserRepository;
import com.jobhook.JobHook.utility.Data;
import com.jobhook.JobHook.utility.Utilities;


import jakarta.mail.internet.MimeMessage;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private ProfileService profileService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<User> optional=userRepository.findByEmail(userDTO.getEmail());
        if(optional.isPresent()){
            throw new JobPortalException("USER_FOUND");
        }
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail(),userDTO.getName()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())){
            throw new JobPortalException("INVALID_CREDENTIALS");
        }
        return user.toDTO(); 
    }

    @Override
    public Boolean sendOTP(String email) throws Exception{
       User user=userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
       MimeMessage mm=mailSender.createMimeMessage();
       MimeMessageHelper message=new MimeMessageHelper(mm,true);
       message.setTo(email);
       message.setSubject("Your Otp Code");
       String genOTP=Utilities.generateOTP();
       OTP otp=new OTP(email,genOTP,LocalDateTime.now());
       otpRepository.save(otp);
       message.setText(Data.getMessage(genOTP, user.getName()),true);
       mailSender.send(mm);
       return true;
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTP otpEntity=otpRepository.findById(email).orElseThrow(()-> new JobPortalException("OTP_NOT_FOUND"));
        if(!otpEntity.getOtpCode().equals(otp)){
            throw new JobPortalException("OTP_INCORRECT");
        }
        return true; 
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        return new ResponseDTO("Password Changed Successfully");
    }
    @Scheduled(fixedRate = 30000)
    public void removeExpiredOtp(){
        LocalDateTime expiry=LocalDateTime.now().minusMinutes(1);
        List<OTP> expiredOTP=otpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOTP.isEmpty()){
            otpRepository.deleteAll(expiredOTP);
            System.out.println("Removed "+expiredOTP.size()+" OTP's");
        }
    }

    @Override
    public UserDTO getUserById(Long id) throws JobPortalException {
        User user=userRepository.findById(id).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        return user.toDTO();
    }

}
