package com.example.gwtapp.server.service.batch;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.service.batch.writer.PlaceRepositoryWriter;
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
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.JsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class PlaceJsonImportBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job placeJsonImportJob(Step placeJsonImportStep) {
        return jobBuilderFactory.get("placeJsonImportJob")
                .start(placeJsonImportStep)
                .build();
    }

    @Bean
    public Step placeJsonImportStep(ItemReader<Place> placeJsonItemReader, ItemWriter<Place> placeJsonImportWriter) {
        return stepBuilderFactory
                .get("placeJsonImportStep")
                .<Place, Place>chunk(100)
                .reader(placeJsonItemReader)
                .processor(placeJsonImportItemProcessor())
                .writer(placeJsonImportWriter)
                .build();
    }

    @Bean
    @StepScope
    public JsonItemReader<Place> placeJsonItemReader(@Value("#{jobParameters['file.input']}") String input) {
        JsonObjectReader<Place> jsonObjectReader = new JacksonJsonObjectReader<>(Place.class);
        return new JsonItemReaderBuilder<Place>().jsonObjectReader(jsonObjectReader)
                .resource(new FileSystemResource(input))
                .name("placeJsonItemReader")
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Place, Place> placeJsonImportItemProcessor() {
        return place -> place;
    }

    @Bean
    @StepScope
    public PlaceRepositoryWriter placeJsonImportWriter() {
        return new PlaceRepositoryWriter();
    }
}
