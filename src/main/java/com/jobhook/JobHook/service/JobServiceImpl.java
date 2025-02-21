package com.jobhook.JobHook.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobhook.JobHook.dto.ApplicantDTO;
import com.jobhook.JobHook.dto.Application;
import com.jobhook.JobHook.dto.ApplicationStatus;
import com.jobhook.JobHook.dto.JobDTO;
import com.jobhook.JobHook.dto.JobStatus;
import com.jobhook.JobHook.entity.Applicant;
import com.jobhook.JobHook.entity.Job;
import com.jobhook.JobHook.exception.JobPortalException;
import com.jobhook.JobHook.repository.JobRepository;
import com.jobhook.JobHook.utility.Utilities;

@Service("jobService")
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
        if(jobDTO.getId()==0){
            jobDTO.setId(Utilities.getNextSequence("jobs"));
            jobDTO.setPostTime(LocalDateTime.now());
            jobDTO.setApplicants(new ArrayList<>());
        }
        else{
            Job job=jobRepository.findById(jobDTO.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
            if(job.getJobStatus().equals(JobStatus.DRAFT) || jobDTO.getJobStatus().equals(JobStatus.CLOSED)){
                jobDTO.setPostTime(LocalDateTime.now());
            }
        }
        
        jobRepository.save(jobDTO.toEntity());
        return jobDTO;
    }

    @Override
    public List<JobDTO> getAllJobs() {
       return jobRepository.findAll().stream().map((job)->job.toDto()).toList();
    }

    @Override
    public JobDTO getJob(Long id) throws JobPortalException {
        Job job=jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        return job.toDto();
    }

    @Override
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {
        Job job=jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> applicants=job.getApplicants();
        if(applicants==null){
            applicants=new ArrayList<>();
        }
        if(applicants.stream().filter((x)->x.getApplicantId()==applicantDTO.getApplicantId()).toList().size()>0){
            throw new JobPortalException("JOB_APPLIED_ALREADY");
        }
        applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
        applicantDTO.setTimeStamp(LocalDateTime.now());
        applicants.add(applicantDTO.toEntity());
        job.setApplicants(applicants);
        jobRepository.save(job);

    }

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException {
        if(jobRepository.findByPostedBy(id).size()==0){
            throw new JobPortalException("NO JOB POSTED");
        }
        return jobRepository.findByPostedBy(id).stream().map((x)->x.toDto()).toList();
    }

    @Override
    public void changeAppStatus(Application application) throws JobPortalException {
        // System.out.println("Received Application ID: " + application.getId());
        // System.out.println("Received Applicant ID: " + application.getApplicantId());
        // System.out.println("Received Status: " + application.getApplicationStatus());
        // System.out.println("Received Interview Time: " + application.getInterviewTime());
        Job job=jobRepository.findById(application.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> applicants=job.getApplicants().stream().map((x)->{
            if(application.getApplicantId()==x.getApplicantId()){
                x.setApplicationStatus(application.getApplicationStatus());
                if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {x.setInterviewTime(application.getInterviewTime());}
            }
            return x;
        }).toList();
        job.setApplicants(applicants);
        jobRepository.save(job);
    }



}
