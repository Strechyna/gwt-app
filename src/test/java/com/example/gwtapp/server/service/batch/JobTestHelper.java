package com.example.gwtapp.server.service.batch;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.domain.model.PlaceTestBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
@Builder
public class JobTestHelper {

    public static final String FILE_INPUT_KEY = "file.input";
    public static final String FILE_OUTPUT_KEY = "file.output";
    public static final String COMPLETED_STATUS = "COMPLETED";

    private final JobLauncherTestUtils jobLauncherTestUtils;
    private final String jobName;
    private final JobParameters jobParameters;

    public void launchJobTest() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        assertThat(actualJobInstance.getJobName()).isEqualTo(jobName);
        assertThat(actualJobExitStatus.getExitCode()).isEqualTo(COMPLETED_STATUS);
    }

    public static JobParameters createJobParameters(String key, String value) {
        return new JobParametersBuilder()
                .addString(key, value)
                .toJobParameters();
    }

    public static Page<Place> createPage() {
        return new PageImpl<>(Arrays.asList(PlaceTestBuilder.build("1"), PlaceTestBuilder.build("2")));
    }
}
