package com.example.SpringStart.commons.dto.product;

import com.example.SpringStart.tables.cartproduct.model.CartProduct;
import com.example.SpringStart.tables.ordersproduct.model.OrdersProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private float price;
    private int ammount;
    private float vat;
    private String category;
    private List<OrdersProduct> ordersProducts;
    private List<CartProduct> cartProducts;
}
