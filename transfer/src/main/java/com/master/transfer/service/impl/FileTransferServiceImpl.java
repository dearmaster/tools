package com.master.transfer.service.impl;

import com.master.transfer.service.FileTransferService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

public class FileTransferServiceImpl implements FileTransferService {

    private static final Logger logger = Logger.getLogger(FileTransferServiceImpl.class);

    private String transferFolder;

    @Override
    public String upload(@Context HttpServletRequest request, @PathParam("filename") String filename) throws Exception {
        logger.info("uploading files....");
        return null;
    }

    @Override
    public String download(@PathParam("filename") String filename) throws Exception {
        logger.info("downloading files....");
        return null;
    }

    public String getTransferFolder() {
        return transferFolder;
    }

    public void setTransferFolder(String transferFolder) {
        this.transferFolder = transferFolder;
    }

}