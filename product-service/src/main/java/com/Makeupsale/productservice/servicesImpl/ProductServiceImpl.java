package com.Makeupsale.productservice.servicesImpl;


import com.Makeupsale.productservice.data_transfer_objects.ProductRequest;
import com.Makeupsale.productservice.data_transfer_objects.ProductResponse;
import com.Makeupsale.productservice.models.Product;
import com.Makeupsale.productservice.repositories.ProductRepository;
import com.Makeupsale.productservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepo;

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products= productRepo.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }
    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    @Override
    public void addProduct(ProductRequest p) {
        Product product= Product.builder()
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build();
        productRepo.save(product);
        log.info("the product {} was saved succesfully",product.getId());

    }
}