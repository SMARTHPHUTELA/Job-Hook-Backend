package com.jobhook.JobHook.service;

import java.util.List;

import com.jobhook.JobHook.dto.ApplicantDTO;
import com.jobhook.JobHook.dto.Application;
import com.jobhook.JobHook.dto.JobDTO;
import com.jobhook.JobHook.exception.JobPortalException;

public interface JobService {
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;
    public List<JobDTO> getAllJobs();
    public JobDTO getJob(Long id) throws JobPortalException;
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException;
    public void changeAppStatus(Application application) throws JobPortalException;

}
