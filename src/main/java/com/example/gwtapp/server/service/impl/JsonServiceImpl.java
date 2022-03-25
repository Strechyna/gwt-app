package com.example.gwtapp.server.service.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class JsonServiceImpl extends CommonFileServiceImpl {

    private static final String EXPORT_FILE_NAME_FORMATTER = "export%s.json";

    @Autowired
    public JsonServiceImpl(Job placeJsonImportJob, Job placeJsonExportJob, JobLauncher jobLauncher, FileStorageServiceImpl fileStorageService) {
        super(placeJsonImportJob, placeJsonExportJob, jobLauncher, fileStorageService);
    }

    @Override
    public Resource exportFile() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        String fileName = String.format(EXPORT_FILE_NAME_FORMATTER, System.currentTimeMillis());
        return exportFile(fileName);
    }

}
