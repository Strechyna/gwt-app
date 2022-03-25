package com.example.gwtapp.server.service.batch;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.service.batch.mapper.PlaceCsvFieldSetMapper;
import com.example.gwtapp.server.service.batch.writer.PlaceRepositoryWriter;
import com.example.gwtapp.server.service.helper.PlaceCsvHeader;
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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class PlaceCsvImportBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job placeCsvImportJob(Step placeCsvImportStep) {
        return jobBuilderFactory.get("placeCsvImportJob")
                .start(placeCsvImportStep)
                .build();
    }

    @Bean
    public Step placeCsvImportStep(ItemReader<Place> placeCsvItemReader, ItemWriter<Place> placeCsvImportWriter) {
        return stepBuilderFactory
                .get("placeCsvImportStep")
                .<Place, Place>chunk(100)
                .reader(placeCsvItemReader)
                .processor(placeCsvImportItemProcessor())
                .writer(placeCsvImportWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Place> placeCsvItemReader(@Value("#{jobParameters['file.input']}") String input) {
        PlaceCsvFieldSetMapper mapper = new PlaceCsvFieldSetMapper();
        return new FlatFileItemReaderBuilder<Place>()
                .name("placeCsvItemReader")
                .resource(new FileSystemResource(input))
                .delimited()
                .names(PlaceCsvHeader.ALL)
                .fieldSetMapper(mapper)
                .build();
    }

    @Bean
    @StepScope
    ItemProcessor<Place, Place> placeCsvImportItemProcessor() {
        return place -> place;
    }

    @Bean
    @StepScope
    public PlaceRepositoryWriter placeCsvImportWriter() {
        return new PlaceRepositoryWriter();
    }

}
