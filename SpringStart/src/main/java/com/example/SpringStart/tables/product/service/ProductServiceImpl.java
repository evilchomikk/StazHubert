package com.example.SpringStart.tables.product.service;

import com.example.SpringStart.tables.product.mapper.ProductMapper;
import com.example.SpringStart.tables.product.model.Product;
import com.example.SpringStart.commons.dto.product.ProductDTO;
import com.example.SpringStart.tables.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuditReader reader;
    private final ProductMapper productMapper;

    @Override
    public void addProduct(ProductDTO productDto) {
        productRepository.save(productMapper.dtoToEntity(productDto));
        productRepository.findByName(productDto.getName()).ifPresent(prod -> Logger.getLogger("ProductService").info("Product added: " + prod.getName()));

    }

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.entityToDto(productRepository.findAll());
    }

    @Override
    public List<ProductDTO> getProductsHistory(Integer revisionNumber) {
        AuditQuery query = reader.createQuery().forEntitiesAtRevision(Product.class, revisionNumber);
        return productMapper.entityToDto(query.getResultList());
    }

    @Override
    public void updateProduct(Integer id, ProductDTO productDto) {
        productRepository.findById(id).ifPresent(prod -> {
            if (productDto.getName() == null)
                productDto.setName(prod.getName());
            if (productDto.getPrice() == 0)
                productDto.setPrice(prod.getPrice());
            if (productDto.getAmmount() == 0)
                productDto.setAmmount(prod.getAmmount());
            if (productDto.getCategory() == null)
                productDto.setCategory(prod.getCategory());
            if (productDto.getVat() == 0)
                productDto.setVat(prod.getVat());
            if (productDto.getDescription() == null)
                productDto.setDescription(prod.getDescription());

            productRepository.save(productMapper.dtoToEntity(productDto));
            Logger.getLogger("ProductService").info("Product updated: " + productDto.getName());
        });
    }


}
