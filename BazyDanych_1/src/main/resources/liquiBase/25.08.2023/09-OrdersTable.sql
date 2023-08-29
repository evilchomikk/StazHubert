--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE orders(
    id INT NOT NULL AUTO_INCREMENT,
    id_customer INT NOT NULL,
    date_of_order DATE NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_customer
    FOREIGN KEY (id_customer)
    REFERENCES customer(id);