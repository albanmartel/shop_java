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

INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Souris' , 'Logitoch', 65 );
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Clavier' , 'Microhard', 49.5 );
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Systeme d''exploitation', 'Fenetres Vistouille', 150);
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Tapis souris','Chapeau Bleu',5 );
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Cle USB 8 To','Syno', 8 );
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Laptop' , 'PH', 1199);
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('CD x 500' ,'CETME', 250);
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('DVD-R x 100','CETME', 99);
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('DVD+R x 100' ,'CETME', 105);
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Batterie Laptop','PH', 80);
INSERT INTO article ( description, brand, unitaryPrice ) VALUES ('Casque Audio','Syno',	105);
INSERT INTO article ( description, brand ) VALUES ('WebCam', 'Logitoch');

CREATE TABLE categorie (
	idCategory INT(4) PRIMARY KEY AUTO_INCREMENT,
	CatName VARCHAR(30) NOT NULL,
	description VARCHAR(100) NOT NULL
);

CREATE TABLE user (
	idUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
);

CREATE TABLE customer (
	id					int(10)			PRIMARY KEY AUTO_INCREMENT,
	name				varchar(20) 	NOT NULL,
	firstName			varchar(20) 	NOT NULL,
	email				varchar(45) 	NOT NULL,
	phone				varchar(45) 	NOT NULL,
	address				varchar(90) 	NOT NULL,
	idUser           	INT(4)   NOT NULL,
	FOREIGN KEY(idUser) REFERENCES user(idUser)
);

CREATE TABLE order (
	IdOrder			int(4)	PRIMARY KEY AUTO_INCREMENT,
	Amount			float(4)	NOT NULL DEFAULT 0,
	DateOrder 		DATE		NOT NULL DEFAULT NOW(),
	IdCustomer      INT(4)   	NOT NULL,
	FOREIGN KEY(IdCustomer) REFERENCES customer(IdCustomer)
);

CREATE TABLE order_item (
	IdOrderItem			int(4)	PRIMARY KEY AUTO_INCREMENT,
	idArticle         INT(4)   NOT NULL,
    idOrder           INT(4)   NOT NULL,
	Quantity		   FLOAT(4) NOT NULL DEFAULT 1,
	unitaryPrice	FLOAT(4)	NOT NULL DEFAULT 0,
    FOREIGN KEY(idArticle) REFERENCES article(idArticle),
	FOREIGN KEY(IdOrder) REFERENCES order(IdOrder)
);

-- ALTER TABLE article ADD COLUMN idCategory INT(4);
-- ALTER TABLE article ADD FOREIGN KEY(idCategory) REFERENCES categorie(idCategory);

-- select idArticle,article.description,brand,unitaryPrice,article.idCategory,CatName,categorie.description 
-- from article inner join categorie where article.idCategory = categorie.idCategory and idArticle=1;

-- SELECT idArticle,article.description,brand,unitaryPrice,CatName FROM article 
-- INNER JOIN categorie WHERE article.idCategory=categorie.idCategory AND idArticle>10 ORDER BY unitaryPrice;
-- SELECT * FROM article;