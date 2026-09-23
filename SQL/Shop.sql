-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS shop;
CREATE DATABASE IF NOT EXISTS shop DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci;;
USE shop;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des articles en vente                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE
  article (
    idArticle int (4) NOT NULL,
    description varchar(30) NOT NULL,
    brand varchar(30) NOT NULL,
    unitaryPrice float NOT NULL DEFAULT 0
  );

INSERT INTO
  article (idArticle, description, brand, unitaryPrice)
VALUES
  (1, 'Souris', 'Logitoch', 65),
  (2, 'Clavier', 'Microhard', 49.5),
  (3, 'Systeme d\'exploitation', 'Fenetres Vistouille', 150),
  (4, 'Tapis souris', 'Chapeau Bleu', 5),
  (5, 'Cle USB 8 To', 'Syno', 8),
  (6, 'Laptop', 'PH', 1199),
  (7, 'CD x 500', 'CETME', 250),
  (8, 'DVD-R x 100', 'CETME', 99),
  (9, 'DVD+R x 100', 'CETME', 105),
  (10, 'Batterie Laptop', 'PH', 80),
  (11, 'Casque Audio', 'Syno', 105),
  (12, 'WebCam', 'Logitoch', 88.74);

CREATE TABLE
  categorie (
    idCategory int (4) NOT NULL,
    catName varchar(30) NOT NULL,
    description varchar(100) NOT NULL
  );

CREATE TABLE
  customer (
    idCustomer int (10) NOT NULL,
    name varchar(20) NOT NULL,
    firstName varchar(20) NOT NULL,
    email varchar(45) NOT NULL,
    phone varchar(45) NOT NULL,
    address varchar(90) NOT NULL,
    idUser int (4) NOT NULL
  );

CREATE TABLE
  customerorder (
    idcustomerOrder int (4) NOT NULL,
    amount float NOT NULL DEFAULT 0,
    datecustomerOrder date NOT NULL DEFAULT current_timestamp(),
    idCustomer int (4) NOT NULL
  );

CREATE TABLE
  customerorder_item (
    idcustomerOrderItem int (4) NOT NULL,
    idArticle int (4) NOT NULL,
    idcustomerOrder int (4) NOT NULL,
    Quantity float NOT NULL DEFAULT 1,
    unitaryPrice float NOT NULL DEFAULT 0
  );

CREATE TABLE
  userShop (
    idUser int (4) NOT NULL,
    login varchar(20) NOT NULL,
    password varchar(20) NOT NULL
  );

ALTER TABLE article ADD PRIMARY KEY (idArticle);

ALTER TABLE categorie ADD PRIMARY KEY (idCategory);

ALTER TABLE customer ADD PRIMARY KEY (idCustomer),
ADD KEY idUser (idUser);

ALTER TABLE customerorder ADD PRIMARY KEY (idcustomerOrder),
ADD KEY idCustomer (idCustomer);

ALTER TABLE customerorder_item ADD PRIMARY KEY (idcustomerOrderItem),
ADD KEY idArticle (idArticle),
ADD KEY idcustomerOrder (idcustomerOrder);

ALTER TABLE userShop ADD PRIMARY KEY (idUser),
ADD UNIQUE KEY login (login);

ALTER TABLE article MODIFY idArticle int (4) NOT NULL AUTO_INCREMENT,
AUTO_INCREMENT = 13;

ALTER TABLE categorie MODIFY idCategory int (4) NOT NULL AUTO_INCREMENT;

ALTER TABLE customer MODIFY idCustomer int (10) NOT NULL AUTO_INCREMENT;

ALTER TABLE customerorder MODIFY idcustomerOrder int (4) NOT NULL AUTO_INCREMENT;

ALTER TABLE customerorder_item