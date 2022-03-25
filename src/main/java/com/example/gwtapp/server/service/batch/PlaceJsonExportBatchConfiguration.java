package com.example.gwtapp.server.service.batch;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.service.batch.reader.PlaceRepositoryReader;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class PlaceJsonExportBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job placeJsonExportJob(Step placeJsonExportStep) {
        return jobBuilderFactory.get("placeJsonExportJob")
                .start(placeJsonExportStep)
                .build();
    }

    @Bean
    public Step placeJsonExportStep(ItemReader<Place> placeJsonExportItemReader, ItemWriter<Place> placeJsonExportWriter) {
        return stepBuilderFactory
                .get("placeJsonExportStep")
                .<Place, Place>chunk(100)
                .reader(placeJsonExportItemReader)
                .processor(placeJsonExportItemProcessor())
                .writer(placeJsonExportWriter)
                .build();
    }

    @Bean
    @StepScope
    public PlaceRepositoryReader placeJsonExportItemReader() {
        return new PlaceRepositoryReader();
    }

    @Bean
    @StepScope
    ItemProcessor<Place, Place> placeJsonExportItemProcessor() {
        return place -> place;
    }

    @Bean
    @StepScope
    public JsonFileItemWriter<Place> placeJsonExportWriter(@Value("#{jobParameters['file.output']}") String output) {
        JacksonJsonObjectMarshaller<Place> marshaller = new JacksonJsonObjectMarshaller<>();
        return new JsonFileItemWriterBuilder<Place>()
                .name("placeJsonExportWriter")
                .jsonObjectMarshaller(marshaller)
                .resource(new FileSystemResource(output))
                .build();
    }
}
