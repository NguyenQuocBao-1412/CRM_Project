package com.cyber.crm.repositoty;

import com.cyber.crm.entity.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<JobsEntity, Integer> {
    @Query("SELECT j FROM jobs j WHERE CURDATE() BETWEEN j.startDate AND j.endDate")
    public List<JobsEntity> getAvailableJobs();
}
