-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS shop;
CREATE DATABASE shop;
USE shop;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des articles en vente                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE article (
	idArticle			int(4)		PRIMARY KEY AUTO_INCREMENT,
	description			varchar(30)	NOT NULL,
	brand				varchar(30)	NOT NULL,
	unitaryPrice		float(8)	NOT NULL DEFAULT 0
);

INSERT INTO article (idArticle, description, brand, unitaryPrice) VALUES
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

CREATE TABLE categorie (
	idCategory INT(4) PRIMARY KEY AUTO_INCREMENT,
	catName VARCHAR(30) NOT NULL,
	description VARCHAR(100) NOT NULL
);

CREATE TABLE user (
    idUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	login				varchar(20)	NOT NULL UNIQUE,
	password			varchar(20)	NOT NULL
);

CREATE TABLE customer (
	idCustomer			int(10)			PRIMARY KEY AUTO_INCREMENT,
	name				varchar(20) 	NOT NULL,
	firstName			varchar(20) 	NOT NULL,
	email				varchar(45) 	NOT NULL,
	phone				varchar(45) 	NOT NULL,
	address				varchar(90) 	NOT NULL,
	idUser           	INT(4)   NOT NULL,
	FOREIGN KEY(idUser) REFERENCES user(idUser)
);

CREATE TABLE customerOrder (
	idcustomerOrder			int(4)	PRIMARY KEY AUTO_INCREMENT,
	amount			float(4)	NOT NULL DEFAULT 0,
	datecustomerOrder 		DATE		NOT NULL DEFAULT NOW(),
	idCustomer      INT(4)   	NOT NULL,
	FOREIGN KEY(idCustomer) REFERENCES customer(idCustomer)
);

CREATE TABLE customerOrder_item (
	idcustomerOrderItem			int(4)	PRIMARY KEY AUTO_INCREMENT,
	idArticle         INT(4)   NOT NULL,
    idcustomerOrder           INT(4)   NOT NULL,
	Quantity		   FLOAT(4) NOT NULL DEFAULT 1,
	unitaryPrice	FLOAT(4)	NOT NULL DEFAULT 0,
    FOREIGN KEY(idArticle) REFERENCES article(idArticle),
	FOREIGN KEY(idcustomerOrder) REFERENCES customerOrder(idcustomerOrder)
);

-- ALTER TABLE article ADD COLUMN idCategory INT(4);
-- ALTER TABLE article ADD FOREIGN KEY(idCategory) REFERENCES categorie(idCategory);

-- select idArticle,article.description,brand,unitaryPrice,article.idCategory,CatName,categorie.description 
-- from article inner join categorie where article.idCategory = categorie.idCategory and idArticle=1;

-- SELECT idArticle,article.description,brand,unitaryPrice,CatName FROM article 
-- INNER JOIN categorie WHERE article.idCategory=categorie.idCategory AND idArticle>10 customerORDER BY unitaryPrice;
-- SELECT * FROM article;