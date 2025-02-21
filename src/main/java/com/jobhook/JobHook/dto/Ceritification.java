package com.jobhook.JobHook.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ceritification {
    private String name;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;
}
