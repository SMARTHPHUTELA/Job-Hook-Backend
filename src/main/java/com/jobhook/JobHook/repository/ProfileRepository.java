package com.jobhook.JobHook.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobhook.JobHook.entity.Profile;

public interface ProfileRepository extends MongoRepository<Profile,Long> {

}
