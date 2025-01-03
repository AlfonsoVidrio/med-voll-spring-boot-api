CREATE TABLE doctors (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    document varchar(255) NOT NULL UNIQUE,
    specialty varchar(255) NOT NULL,
    street varchar(255) NOT NULL,
    district varchar(255) NOT NULL,
    complement varchar(255),
    number varchar(255),
    city varchar(255) NOT NULL,
    PRIMARY KEY (id)
);