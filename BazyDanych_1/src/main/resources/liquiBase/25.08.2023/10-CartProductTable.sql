--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE cart_product(
id_cart INT NOT NULL,
id_product INT NOT NULL,
ammount INT NOT NULL,
PRIMARY KEY (id_cart, id_product)
);

ALTER TABLE cart_product
    ADD CONSTRAINT fk_cart_product_cart
    FOREIGN KEY (id_cart)
    REFERENCES cart(id);

ALTER TABLE cart_product
    ADD CONSTRAINT fk_cart_product_product
    FOREIGN KEY (id_product)
    REFERENCES product(id);