CREATE USER 'java'@'localhost' IDENTIFIED BY 'java123';

GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'java'@'localhost' WITH GRANT OPTION;

CREATE DATABASE java;

USE java;

CREATE TABLE students (
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL ,
    age int(3) NOT NULL,
    city varchar(255) NOT NULL,
    created TIMESTAMP default CURRENT_TIMESTAMP
);

INSERT INTO students (name, age, city) VALUES
('Romy Saputra Sihananda', 17, 'Blitar');