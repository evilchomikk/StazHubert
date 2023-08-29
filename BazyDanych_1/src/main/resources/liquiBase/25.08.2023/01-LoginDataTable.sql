--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE login_data (
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);