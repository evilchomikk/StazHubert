package com.example.SpringStart.tables.product.service;

import com.example.SpringStart.tables.product.model.ProductDTO;

import java.util.List;

public interface ProductService {

    void addProduct(ProductDTO product);

    List<ProductDTO> getProducts();

    List<ProductDTO> getProductsHistory(Integer revisionNumber);

    void updateProduct(Integer id, ProductDTO product);

}
