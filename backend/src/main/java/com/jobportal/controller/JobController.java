package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        try {
            List<Job> jobs = jobService.getAllJobs();
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            logger.error("Error fetching jobs: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        try {
            return jobService.getJobById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error fetching job with id {}: ", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        try {
            logger.info("Creating new job: {}", job);
            Job createdJob = jobService.createJob(job);
            return ResponseEntity.ok(createdJob);
        } catch (Exception e) {
            logger.error("Error creating job: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        try {
            logger.info("Updating job with id {}: {}", id, jobDetails);
            Job updatedJob = jobService.updateJob(id, jobDetails);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            logger.error("Error updating job with id {}: ", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error updating job with id {}: ", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        try {
            logger.info("Deleting job with id: {}", id);
            jobService.deleteJob(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Error deleting job with id {}: ", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error deleting job with id {}: ", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
} 