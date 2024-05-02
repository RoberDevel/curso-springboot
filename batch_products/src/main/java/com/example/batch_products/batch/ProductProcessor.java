package com.example.batch_products.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.example.batch_products.model.NewProduct;
import com.example.batch_products.model.Product;

public class ProductProcessor implements ItemProcessor<Product, NewProduct> {

    @Override
    @Nullable
    public NewProduct process(Product product) throws Exception {
        NewProduct newProduct = new NewProduct(product.getId(), product.getName(), product.getDescription(),
                product.getPrice());

        return newProduct;
    }

}
