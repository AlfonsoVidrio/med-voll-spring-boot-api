CREATE TABLE patients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE ,
    document VARCHAR(20) NOT NULL UNIQUE,
    street VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    number VARCHAR(20),
    city VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    active tinyint NOT NULL,

    PRIMARY KEY (id)

);