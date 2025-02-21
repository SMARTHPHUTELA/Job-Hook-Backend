package com.jobhook.JobHook.entity;

import java.util.Base64;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jobhook.JobHook.dto.Ceritification;
import com.jobhook.JobHook.dto.Experience;
import com.jobhook.JobHook.dto.ProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private Long id;
    private String email;
    private String name;
    private String jobTitile;
    private String company;
    private String location;
    private String about;
    private byte[] picture;
    private List<String> skills;
    private List<Experience> experiences;
    private List<Ceritification> certifications;
    private List<Long> savedJobs;

    public ProfileDTO toDTO(){
        return new ProfileDTO(this.id,this.email,this.name,this.jobTitile,this.company,this.location,this.about,this.picture!=null?Base64.getEncoder().encodeToString(this.picture):null,this.skills,this.experiences,this.certifications,this.savedJobs);
    }
}
