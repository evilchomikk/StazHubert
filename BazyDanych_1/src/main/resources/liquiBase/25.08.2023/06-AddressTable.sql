--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE address (
    id INT NOT NULL AUTO_INCREMENT,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL,
    base_address BOOLEAN NOT NULL,
    id_customer INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT FK_address_customer_id
    FOREIGN KEY (id_customer)
    REFERENCES customer(id);