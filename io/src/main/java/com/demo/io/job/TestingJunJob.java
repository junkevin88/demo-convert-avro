package com.demo.io.job;


import com.demo.io.entity.TestingJun;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@EnableBatchProcessing
@Configuration
public class TestingJunJob {
    @Bean
    public FlatFileItemReader<TestingJun> reader() throws IOException {
        FlatFileItemReader<TestingJun> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/data/testing.txt")); // Adjust path to your local file

        // Set line mapper to parse text data
        DefaultLineMapper<TestingJun> lineMapper = new DefaultLineMapper<>();
        LineTokenizer tokenizer = new LineTokenizer() {
            @Override
            public FieldSet tokenize(String line) {
                return null;
            }
        };

        // No arguments for TXT

        BeanWrapperFieldSetMapper<TestingJun> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(TestingJun.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }




}
