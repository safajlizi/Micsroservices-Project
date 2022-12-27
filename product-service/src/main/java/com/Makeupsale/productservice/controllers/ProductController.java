package com.Makeupsale.productservice.controllers;

import com.Makeupsale.productservice.data_transfer_objects.ProductRequest;
import com.Makeupsale.productservice.data_transfer_objects.ProductResponse;
import com.Makeupsale.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService productServ;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productServ.getAllProducts();

    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest p ) {

        productServ.addProduct(p);
    }
}
