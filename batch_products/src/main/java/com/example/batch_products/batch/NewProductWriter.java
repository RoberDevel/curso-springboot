package com.example.batch_products.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.batch_products.model.NewProduct;
import com.example.batch_products.repository.NewProductRepository;

@Component
public class NewProductWriter implements ItemWriter<NewProduct> {

    @Autowired
    NewProductRepository newProductRepository;

    @Override
    public void write(@NonNull Chunk<? extends NewProduct> chunk) throws Exception {
        for (NewProduct newProduct : chunk.getItems()) {
            newProductRepository.save(newProduct);
        }

    }

}
