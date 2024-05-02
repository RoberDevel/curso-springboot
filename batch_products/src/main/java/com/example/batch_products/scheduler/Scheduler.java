package com.example.batch_products.scheduler;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Scheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job runJob;

    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void runJobScheduler() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                .addLong("uniqueness", System.nanoTime())
                .toJobParameters();

        jobLauncher.run(runJob, jobParameters);

    }

    public void ne(Timestamp timestamp) {
        DateTime dateTime;
    }

}
