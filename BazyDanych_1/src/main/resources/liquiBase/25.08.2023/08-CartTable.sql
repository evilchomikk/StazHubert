--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE cart(
    id INT NOT NULL AUTO_INCREMENT,
    id_customer INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE cart
    ADD CONSTRAINT fk_cart_customer
    FOREIGN KEY (id_customer)
    REFERENCES customer(id);