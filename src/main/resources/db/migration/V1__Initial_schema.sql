create table IF NOT exists roles(
    id INT not null auto_increment,
    name varchar(255),
primary key (id)) engine=InnoDB;

create table IF NOT exists users (
    id INT not null auto_increment,
    username varchar(100),
    password VARCHAR(100) NOT NULL,
    enabled boolean,
primary key (id)) engine=InnoDB;

CREATE TABLE IF NOT exists beers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(255),
    brand VARCHAR(255),
    name VARCHAR(255),
    style VARCHAR(255),
    hop VARCHAR(255),
    yeast VARCHAR(255),
    malts VARCHAR(255),
    ibu VARCHAR(255),
    alcohol VARCHAR(255),
    blg VARCHAR(255)
    );


CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    uid VARCHAR(255),
    gender VARCHAR(50),
    phone_number VARCHAR(50),
    email VARCHAR(50),
    username VARCHAR(50),
    date_of_birth DATE,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE SET NULL
);



# On Utilise toujours des liste de roles, pour permettre l'ajout de nouveaux roles a un utilisateur
CREATE TABLE IF NOT exists users_roles (
    users_id INT,
    roles_id INT,
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id),
    PRIMARY KEY (users_id, roles_id)
);

# Le nom des roles doit commencer par ROLE_ en spring security
INSERT INTO roles (name) VALUES
('ROLE_USER'),
('ROLE_REBOND'),
('ROLE_SCRAP')
;

