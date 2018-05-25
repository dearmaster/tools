package com.master.report.batch.reader;

import com.master.report.batch.model.AuditReportItem;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.database.JdbcCursorItemReader;

public class AuditReportItemReader extends JdbcCursorItemReader<AuditReportItem> implements ItemStream {

    private static final Logger logger = Logger.getLogger(AuditReportItemReader.class);

    public AuditReportItemReader() {
        logger.info(this.getClass().getSimpleName() + " initialized....");
    }

}