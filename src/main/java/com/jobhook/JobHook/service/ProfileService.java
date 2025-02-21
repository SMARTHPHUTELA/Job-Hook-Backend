package com.jobhook.JobHook.service;

import java.util.List;

import com.jobhook.JobHook.dto.ProfileDTO;
import com.jobhook.JobHook.exception.JobPortalException;

public interface ProfileService {
    public Long createProfile(String email,String name) throws JobPortalException;
    public ProfileDTO getProfile(Long id) throws JobPortalException;
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;
    public List<ProfileDTO> getAllProfiles();
}
