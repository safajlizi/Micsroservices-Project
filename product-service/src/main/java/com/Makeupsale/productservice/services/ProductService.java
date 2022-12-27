package com.Makeupsale.productservice.services;


import com.Makeupsale.productservice.data_transfer_objects.ProductRequest;
import com.Makeupsale.productservice.data_transfer_objects.ProductResponse;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> getAllProducts();
    public void addProduct(ProductRequest p);
}
