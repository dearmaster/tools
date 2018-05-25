package com.master.report;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:audit-report-generation-job-launcher-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuditReportGenerationIT {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testAuditReportGeneration() throws Throwable {
        final JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        final List<Throwable> throwables = jobExecution.getAllFailureExceptions();

        if(throwables.size() >0) {
            throw throwables.get(0); //throw the first exception
        }

        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }

}