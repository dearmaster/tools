package com.master.report.batch.writer;

import com.master.report.batch.model.AuditReportItem;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class AuditReportItemWriter implements ItemWriter<AuditReportItem> {

    private static final Logger logger = Logger.getLogger(AuditReportItemWriter.class);

    @Override
    public void write(List<? extends AuditReportItem> list) throws Exception {
        logger.info("MockUp generating report for user:  " + list.get(0).getUserId());
    }

}