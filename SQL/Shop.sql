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
	IdArticle			int(4)		PRIMARY KEY AUTO_INCREMENT,
	Description			varchar(30)	NOT NULL,
	Brand				varchar(30)	NOT NULL,
	UnitaryPrice		float(8)	NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris'     ,	'Logitoch', 65 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier'    ,	'Microhard', 49.5 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Systeme d''exploitation',	'Fenetres Vistouille',	150 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Tapis souris', 'Chapeau Bleu',5 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Cle USB 8 To', 'Syno', 8 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop'      , 	'PH',	1199 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'CD x 500'    , 'CETME', 250 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD-R x 100' , 'CETME', 99 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD+R x 100' , 'CETME', 105 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Batterie Laptop', 'PH',	80 );
INSERT INTO article ( Description, Brand, UnitaryPrice ) VALUES ( 'Casque Audio', 'Syno',	105 );
INSERT INTO article ( Description, Brand ) VALUES ( 'WebCam'      , 	'Logitoch' );

SELECT * FROM article;

CREATE TABLE categorie (
	IdCategory INT(4) PRIMARY KEY AUTO_INCREMENT,
	CatName VARCHAR(30) NOT NULL,
	Description VARCHAR(100) NOT NULL
 )

-- ALTER TABLE article ADD COLUMN IdCategory INT(4);
-- ALTER TABLE article ADD FOREIGN KEY(IdCategory) REFERENCES categorie(IdCategory);

-- select IdArticle,article.Description,Brand,UnitaryPrice,article.IdCategory,CatName,categorie.Description 
-- from article inner join categorie where article.IdCategory = categorie.IdCategory and IdArticle=1;

-- SELECT IdArticle,article.Description,brand,UnitaryPrice,CatName FROM article 
-- INNER JOIN categorie WHERE article.IdCategory=categorie.IdCategory AND IdArticle>10 ORDER BY UnitaryPrice;

CREATE TABLE order (
	IdOrder			int(4)	PRIMARY KEY AUTO_INCREMENT,
	Amount			float(4)	NOT NULL DEFAULT 0,
	DateOrder 		DATE		NOT NULL DEFAULT NOW(),
	IdCustomer      INT(4)   	NOT NULL,
	FOREIGN KEY(IdCustomer) REFERENCES T_Customers(IdCustomer)
) ENGINE = InnoDB;


CREATE TABLE order_item (
	IdOrderItem			int(4)	PRIMARY KEY AUTO_INCREMENT,
	
	IdArticle         INT(4)   NOT NULL,
	FOREIGN KEY(IdArticle) REFERENCES article(IdArticle),
	
	Quantity				FLOAT(4) NOT NULL DEFAULT 1,
	UnitaryPrice		FLOAT(4)	NOT NULL DEFAULT 0,
	
	IdOrder           INT(4)   NOT NULL,
	FOREIGN KEY(IdOrder) REFERENCES order(IdOrder)
) ENGINE = InnoDB;

CREATE TABLE T_Users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
) ENGINE = InnoDB;

CREATE TABLE T_Customers (
	id					int(10)			PRIMARY KEY AUTO_INCREMENT,
	name				varchar(20) 	NOT NULL,
	firstName			varchar(20) 	NOT NULL,
	email				varchar(45) 	NOT NULL,	
	phone				varchar(45) 	NOT NULL,
	address				varchar(90) 	NOT NULL,
	IdUser           	INT(4)   NOT NULL,
	FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser)	
) ENGINE = InnoDB;