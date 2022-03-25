package com.example.gwtapp.server.service.batch;

import com.example.gwtapp.server.domain.repository.PlaceRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { PlaceCsvImportBatchConfiguration.class })
public class PlaceCsvImportJobTest {

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @MockBean
    private PlaceRepository placeRepository;

    @After
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }


    private final static String TEST_INPUT = "src/test/resources/input.csv";
    private final static String JOB_NAME = "placeCsvImportJob";

    @Test
    public void launchJobTest() throws Exception {
        JobTestHelper helper = JobTestHelper.builder()
                .jobLauncherTestUtils(jobLauncherTestUtils)
                .jobName(JOB_NAME)
                .jobParameters(JobTestHelper.createJobParameters(JobTestHelper.FILE_INPUT_KEY, TEST_INPUT))
                .build();
        helper.launchJobTest();

        Mockito.verify(placeRepository).saveAll(any());
    }
}
