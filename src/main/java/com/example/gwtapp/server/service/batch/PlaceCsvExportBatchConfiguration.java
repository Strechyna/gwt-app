package com.example.gwtapp.server.service.batch;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.service.batch.extractor.PlaceCsvFieldExtractor;
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
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class PlaceCsvExportBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job placeCsvExportJob(Step placeCsvExportStep) {
        return jobBuilderFactory.get("placeCsvExportJob")
                .start(placeCsvExportStep)
                .build();
    }

    @Bean
    public Step placeCsvExportStep(ItemReader<Place> placeCsvExportItemReader, ItemWriter<Place> placeCsvExportWriter) {
        return stepBuilderFactory
                .get("placeCsvExportStep")
                .<Place, Place>chunk(100)
                .reader(placeCsvExportItemReader)
                .processor(placeCsvExportItemProcessor())
                .writer(placeCsvExportWriter)
                .build();
    }

    @Bean
    @StepScope
    public PlaceRepositoryReader placeCsvExportItemReader() {
        return new PlaceRepositoryReader();
    }

    @Bean
    @StepScope
    ItemProcessor<Place, Place> placeCsvExportItemProcessor() {
        return place -> place;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Place> placeCsvExportWriter(@Value("#{jobParameters['file.output']}") String output) {
        FieldExtractor<Place> extractor = new PlaceCsvFieldExtractor();
        return new FlatFileItemWriterBuilder<Place>()
                .name("itemWriter")
                .resource(new FileSystemResource(output))
                .delimited()
                .fieldExtractor(extractor)
                .build();
    }
}
