package com.jobhook.JobHook.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobhook.JobHook.dto.ProfileDTO;
import com.jobhook.JobHook.exception.JobPortalException;
import com.jobhook.JobHook.service.ProfileService;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profiles")
public class ProfileAPI {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO>getProfile(@PathVariable Long id)throws JobPortalException{
        ProfileDTO profileDTO = profileService.getProfile(id);
        return new ResponseEntity<>(profileDTO,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO)throws JobPortalException{
        ProfileDTO profileDTO2=profileService.updateProfile(profileDTO);
        return new ResponseEntity<>(profileDTO2,HttpStatus.OK);
    }

    @GetMapping("getAllProfiles")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(){
        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
    }
}
