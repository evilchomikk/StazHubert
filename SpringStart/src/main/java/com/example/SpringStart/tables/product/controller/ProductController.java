package com.example.SpringStart.tables.product.controller;

import com.example.SpringStart.tables.product.model.ProductDTO;
import com.example.SpringStart.tables.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    private void addProduct(@RequestBody ProductDTO productDto) {
        productService.addProduct(productDto);
    }

    @GetMapping("/get")
    private List<ProductDTO> getProduct() {
        return productService.getProducts();
    }

    @GetMapping("/getHistory/{revisionNumber}")
    private List<ProductDTO> getProductHistory(@PathVariable Integer revisionNumber) {
        return productService.getProductsHistory(revisionNumber);
    }

    @PostMapping("/update/{id}")
    private void updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDto) {
        productService.updateProduct(id, productDto);
    }

}
