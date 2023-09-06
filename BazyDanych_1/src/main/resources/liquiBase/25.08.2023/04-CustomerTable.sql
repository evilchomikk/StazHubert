--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE customer(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(255),
last_name VARCHAR(255),
id_login_data INT NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE customer
    ADD CONSTRAINT fk_customer_login_data
    FOREIGN KEY (id_login_data)
    REFERENCES login_data(id);