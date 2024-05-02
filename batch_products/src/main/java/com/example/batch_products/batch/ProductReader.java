package com.example.batch_products.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.*;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.batch_products.model.Product;
import com.example.batch_products.repository.ProductRepository;

@Component
public class ProductReader implements ItemReader<Product> {

    @Autowired
    ProductRepository productRepository;

    @Override
    @Nullable
    public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        RepositoryItemReader<Product> reader = new RepositoryItemReader<>();
        reader.setRepository(productRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(1);
        reader.setSort(sortBy("id", Sort.Direction.ASC));
        try {
            reader.afterPropertiesSet();
            reader.open(new ExecutionContext());

            return reader.read();
        } finally {
            reader.close();
        }

    }

    private Map<String, Sort.Direction> sortBy(String fieldName, Sort.Direction direction) {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put(fieldName, direction);
        return sortMap;
    }

}
