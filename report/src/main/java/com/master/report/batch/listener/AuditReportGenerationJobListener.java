package com.master.report.batch.listener;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class AuditReportGenerationJobListener implements JobExecutionListener {

    private static final Logger logger = Logger.getLogger(AuditReportGenerationJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Before Job, MockUp inserting JOB STARTING flag into DB, Or prepare for the item list which would be used later");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("After job");
    }

}