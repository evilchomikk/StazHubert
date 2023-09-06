package com.example.SpringStart.tables.product.mapper;

import com.example.SpringStart.tables.product.model.Product;
import com.example.SpringStart.tables.product.model.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToEntity(ProductDTO productDto);

    ProductDTO entityToDto(Product product);

    List<ProductDTO> entityToDto(List<Product> product);

    List<Product> dtoToEntity(List<ProductDTO> productDto);
}
