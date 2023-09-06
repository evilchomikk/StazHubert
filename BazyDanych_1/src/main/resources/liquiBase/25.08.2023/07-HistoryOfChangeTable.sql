--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE history_of_change(
id INT NOT NULL AUTO_INCREMENT,
id_employee INT NOT NULL,
id_product INT NOT NULL,
date_of_change DATE NOT NULL,
old_price FLOAT NOT NULL,
new_price FLOAT NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE history_of_change
    ADD CONSTRAINT fk_history_of_change_employee
    FOREIGN KEY (id_employee)
    REFERENCES employee(id);

ALTER TABLE history_of_change
    ADD CONSTRAINT fk_history_of_change_product
    FOREIGN KEY (id_product)
    REFERENCES product(id);