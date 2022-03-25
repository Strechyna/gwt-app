package com.example.gwtapp.server.service.impl;

import com.example.gwtapp.server.service.api.FileService;
import com.example.gwtapp.server.service.api.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
public abstract class CommonFileServiceImpl implements FileService {

    private final Job importJob;
    private final Job exportJob;
    private final JobLauncher jobLauncher;
    private final FileStorageService fileStorageService;

    private static final String TIME = "time";
    private static final String FILE_INPUT = "file.input";
    private static final String FILE_OUTPUT = "file.output";

    @Override
    public void importFile(MultipartFile file) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException, IOException {
        String path = fileStorageService.store(file);

        runJob(importJob, FILE_INPUT, path);
    }

    protected Resource exportFile(String fileName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        String path = fileStorageService.getFilePath(fileName).toString();
        runJob(exportJob, FILE_OUTPUT, path);

        return fileStorageService.load(fileName);
    }

    private void runJob(Job job, String key, String path) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(job, new JobParametersBuilder()
                .addString(key, path)
                .addLong(TIME, System.currentTimeMillis())
                .toJobParameters());
    }
}
