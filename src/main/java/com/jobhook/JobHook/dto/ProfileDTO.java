package com.jobhook.JobHook.dto;

import java.util.Base64;
import java.util.List;

import com.jobhook.JobHook.entity.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String email;
    private String name;
    private String jobTitile;
    private String company;
    private String location;
    private String about;
    private String picture;
    private List<String> skills;
    private List<Experience> experiences;
    private List<Ceritification> certifications;
    private List<Long> savedJobs;

    public Profile toEntity(){
        return new Profile(this.id,this.email,this.name,this.jobTitile,this.company,this.location,this.about,this.picture!=null?Base64.getDecoder().decode(this.picture):null, this.skills,this.experiences,this.certifications,this.savedJobs);
    }

}
