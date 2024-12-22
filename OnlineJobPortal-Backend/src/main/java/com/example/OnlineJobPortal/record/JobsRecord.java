package com.example.OnlineJobPortal.record;

import java.util.Date;

public record JobsRecord( int id, String title, String companyName, String jobDescription, String skills, String salaryRange, Date createdDate) {
}
