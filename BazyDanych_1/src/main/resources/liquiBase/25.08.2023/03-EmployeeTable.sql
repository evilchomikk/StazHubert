--liquibase formatted sql
--changeset kulkah:1
CREATE TABLE employee(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(255),
last_name VARCHAR(255),
id_login_data INT NOT NULL,
id_role INT NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT fk_employee_login_data
    FOREIGN KEY (id_login_data)
    REFERENCES login_data(id);

ALTER TABLE employee
    ADD CONSTRAINT fk_employee_role
    FOREIGN KEY (id_role)
    REFERENCES role(id);