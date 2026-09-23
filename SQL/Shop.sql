-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS shop;
CREATE DATABASE shop;
USE shop;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des articles en vente                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE articles (
	IdArticle			int(4)		PRIMARY KEY AUTO_INCREMENT,
	Description			varchar(30)	NOT NULL,
	Brand				varchar(30)	NOT NULL,
	UnitaryPrice		float(8)	NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris'     ,	'Logitoch', 65 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier'    ,	'Microhard', 49.5 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Systeme d''exploitation',	'Fenetres Vistouille',	150 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Tapis souris', 'Chapeau Bleu',5 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Cle USB 8 To', 'Syno', 8 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop'      , 	'PH',	1199 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'CD x 500'    , 'CETME', 250 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD-R x 100' , 'CETME', 99 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD+R x 100' , 'CETME', 105 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Batterie Laptop', 'PH',	80 );
INSERT INTO articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Casque Audio', 'Syno',	105 );
INSERT INTO articles ( Description, Brand ) VALUES ( 'WebCam'      , 	'Logitoch' );

SELECT * FROM articles;

CREATE TABLE categories (
	IdCategory INT(4) PRIMARY KEY AUTO_INCREMENT,
	CatName VARCHAR(30) NOT NULL,
	Description VARCHAR(100) NOT NULL
 )

-- ALTER TABLE articles ADD COLUMN IdCategory INT(4);
-- ALTER TABLE articles ADD FOREIGN KEY(IdCategory) REFERENCES categories(IdCategory);

-- select IdArticle,articles.Description,Brand,UnitaryPrice,articles.IdCategory,CatName,categories.Description 
-- from articles inner join categories where articles.IdCategory = categories.IdCategory and IdArticle=1;

-- SELECT IdArticle,articles.Description,brand,UnitaryPrice,CatName FROM articles 
-- INNER JOIN categories WHERE articles.IdCategory=categories.IdCategory AND IdArticle>10 ORDER BY UnitaryPrice;

CREATE TABLE orders (
	IdOrder			int(4)	PRIMARY KEY AUTO_INCREMENT,
	Amount			float(4)	NOT NULL DEFAULT 0,
	DateOrder 		DATE		NOT NULL DEFAULT NOW(),
	IdCustomer      INT(4)   	NOT NULL,
	FOREIGN KEY(IdCustomer) REFERENCES customers(IdCustomer)
) ENGINE = InnoDB;


CREATE TABLE order_items (
	IdOrderItem			int(4)	PRIMARY KEY AUTO_INCREMENT,
	
	IdArticle         INT(4)   NOT NULL,
	FOREIGN KEY(IdArticle) REFERENCES articles(IdArticle),
	
	Quantity				FLOAT(4) NOT NULL DEFAULT 1,
	UnitaryPrice		FLOAT(4)	NOT NULL DEFAULT 0,
	
	IdOrder           INT(4)   NOT NULL,
	FOREIGN KEY(IdOrder) REFERENCES orders(IdOrder)
) ENGINE = InnoDB;

CREATE TABLE users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
) ENGINE = InnoDB;

CREATE TABLE customers (
	id					int(10)			PRIMARY KEY AUTO_INCREMENT,
	name				varchar(20) 	NOT NULL,
	firstName			varchar(20) 	NOT NULL,
	email				varchar(45) 	NOT NULL,
	phone				varchar(45) 	NOT NULL,
	address				varchar(90) 	NOT NULL,
	IdUser           	INT(4)   NOT NULL,
	FOREIGN KEY(IdUser) REFERENCES users(IdUser)
) ENGINE = InnoDB;
