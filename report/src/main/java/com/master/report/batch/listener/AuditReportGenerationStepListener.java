package com.master.report.batch.listener;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class AuditReportGenerationStepListener implements StepExecutionListener {

    private static final Logger logger = Logger.getLogger(AuditReportGenerationStepListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("Before step[" + stepExecution.getStepName() + "]: " + stepExecution.getStatus());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("After step[" + stepExecution.getStepName() + "]: " + stepExecution.getStatus());
        return stepExecution.getExitStatus();
    }

}