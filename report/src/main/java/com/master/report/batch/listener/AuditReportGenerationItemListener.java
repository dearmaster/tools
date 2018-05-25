package com.master.report.batch.listener;

import org.apache.log4j.Logger;

import javax.batch.api.chunk.listener.ItemWriteListener;
import java.util.List;

public class AuditReportGenerationItemListener implements ItemWriteListener {

    private static final Logger logger = Logger.getLogger(AuditReportGenerationItemListener.class);

    @Override
    public void beforeWrite(List<Object> list) throws Exception {
        logger.info("Before write: " + list.get(0));
    }

    @Override
    public void afterWrite(List<Object> list) throws Exception {
        logger.info("MockUp Inserting record in DB to mark the current item finished without any issue:  " + list.get(0));
    }

    @Override
    public void onWriteError(List<Object> list, Exception e) throws Exception {
        logger.info("MockUp Inserting record in DB to mark the current item failed in generation:  " + list.get(0));
    }

}