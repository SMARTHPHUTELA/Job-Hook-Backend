package com.jobhook.JobHook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobhook.JobHook.dto.ProfileDTO;
import com.jobhook.JobHook.entity.Profile;
import com.jobhook.JobHook.exception.JobPortalException;
import com.jobhook.JobHook.repository.ProfileRepository;
import com.jobhook.JobHook.utility.Utilities;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email,String name) throws JobPortalException {
        Profile profile=new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setName(name);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profile.setSavedJobs(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
        
    }

    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        Profile prfl= profileRepository.findById(id).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND"));
        return prfl.toDTO();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        // Profile prfl=profileRepository.findById(profileDTO.getId()).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND"));
        // profileRepository.save(prfl);
        // return prfl.toDTO();
        profileRepository.findById(profileDTO.getId()).orElseThrow(()->new JobPortalException("PROFILE_NOT_FOUND"));
        profileRepository.save(profileDTO.toEntity());
        return profileDTO;
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        List<ProfileDTO> allProfiles=new ArrayList<>();
        allProfiles=profileRepository.findAll().stream().map((x)->x.toDTO()).toList();
        return allProfiles;
    }

}
