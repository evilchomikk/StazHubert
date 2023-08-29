--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE orders_product(
    id_order INT NOT NULL,
    id_product INT NOT NULL,
    ammount INT NOT NULL,
    price FLOAT NOT NULL,
    PRIMARY KEY (id_order, id_product)
);

ALTER TABLE orders_product
    ADD CONSTRAINT fk_orders_product_order
    FOREIGN KEY (id_order)
    REFERENCES orders(id);

ALTER TABLE orders_product
    ADD CONSTRAINT fk_orders_product_product
    FOREIGN KEY (id_product)
    REFERENCES product(id);