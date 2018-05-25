package com.master.transfer.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("file")
public interface FileTransferService {

    @PostMapping("/upload/{filename}")
    @Consumes( {MediaType.MULTIPART_FORM_DATA})
    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    String upload(@Context HttpServletRequest request, @PathParam("filename") String filename) throws Exception;

    @GetMapping("/download/{filename}")
    @Consumes( {MediaType.MULTIPART_FORM_DATA})
    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    String download(@PathParam("filename") String filename) throws Exception;

}