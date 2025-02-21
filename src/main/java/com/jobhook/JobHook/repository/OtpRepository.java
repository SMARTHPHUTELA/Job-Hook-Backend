package com.jobhook.JobHook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobhook.JobHook.entity.OTP;
import java.time.LocalDateTime;


public interface OtpRepository extends MongoRepository<OTP,String> {
    List<OTP> findByCreationTimeBefore(LocalDateTime expiry);
}