package com.example.batch_products.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch_products.model.NewProduct;
import com.example.batch_products.model.Product;
import com.example.batch_products.repository.NewProductRepository;
import com.example.batch_products.repository.ProductRepository;

@Configuration
public class ConfigBatch {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    NewProductRepository newProductRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    private Map<String, Sort.Direction> sortBy(String fieldName, Sort.Direction direction) {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put(fieldName, direction);
        return sortMap;
    }

    @Bean
    RepositoryItemReader<Product> reader() {

        RepositoryItemReader<Product> reader = new RepositoryItemReader<>();
        reader.setRepository(productRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(1);
        reader.setSort(sortBy("id", Sort.Direction.ASC));
        return reader;

    }

    @Bean
    ProductProcessor processor() {
        return new ProductProcessor();
    }

    @Bean
    NewProductWriter writer() {
        return new NewProductWriter();
    }

    @Bean
    Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Product, NewProduct>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    Job job1() {

        return new JobBuilder("job1", jobRepository)
                .start(step1())
                .incrementer(new RunIdIncrementer())
                .build();

    }

}
