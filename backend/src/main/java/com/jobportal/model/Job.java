package com.jobportal.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_website")
    private String companyWebsite;

    @Column(name = "company_email", nullable = false)
    private String companyEmail;

    @Column(name = "posted_date", nullable = false)
    private LocalDateTime postedDate;

    @PrePersist
    protected void onCreate() {
        postedDate = LocalDateTime.now();
    }
} 