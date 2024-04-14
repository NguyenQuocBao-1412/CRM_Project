package com.cyber.crm.service;

import com.cyber.crm.entity.JobsEntity;
import com.cyber.crm.repositoty.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobsRepository jobsRepository;

    public List<JobsEntity> getAllJobs() {
        return jobsRepository.findAll();
    }

    public JobsEntity getJobById(int id) {
        Optional<JobsEntity> optionalJobs = jobsRepository.findById(id);

        JobsEntity job = new JobsEntity();
        if(optionalJobs.isPresent()) {
            job = optionalJobs.get();
        }

        return job;
    }

    public boolean saveJob(JobsEntity job) {
        jobsRepository.save(job);

        return true;
    }

    public boolean deleteJobById(int id) {
        jobsRepository.deleteById(id);

        return true;
    }

    public List<JobsEntity> getAvailableJob() {
        List<JobsEntity> listJobs = jobsRepository.getAvailableJobs();
        return listJobs;
    }
}
