--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE product(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
price FLOAT NOT NULL,
description VARCHAR(255),
category VARCHAR(255),
ammount INT,
vat float,
PRIMARY KEY (id)
);
