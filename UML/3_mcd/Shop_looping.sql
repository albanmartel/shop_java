CREATE TABLE category(
   idCategory INT (4),
   catName VARCHAR(30) NOT NULL,
   description VARCHAR(100) NOT NULL,
   PRIMARY KEY(idCategory)
);

CREATE TABLE customerOrder(
   idCustomerOrder INT (4),
   amount REAL NOT NULL DEFAULT 0,
   dateOrder DATE NOT NULL DEFAULT current_timestamp(),
   PRIMARY KEY(idCustomerOrder)
);

CREATE TABLE user_(
   IdUser INT (4),
   login VARCHAR(20) NOT NULL,
   password VARCHAR(20) NOT NULL,
   PRIMARY KEY(IdUser)
);

CREATE TABLE article(
   idArticle INT (4),
   description VARCHAR(30) NOT NULL,
   brand VARCHAR(30) NOT NULL,
   unitaryPrice REAL NOT NULL DEFAULT 0,
   idCategory INT,
   PRIMARY KEY(idArticle),
   FOREIGN KEY(idCategory) REFERENCES category(idCategory)
);

CREATE TABLE customer(
   idCustomer INT (10),
   name VARCHAR(20) NOT NULL,
   firstName VARCHAR(20) NOT NULL,
   email VARCHAR(45) NOT NULL,
   phone VARCHAR(45) NOT NULL,
   address VARCHAR(90) NOT NULL,
   idCustomerOrder INT NOT NULL,
   IdUser INT NOT NULL,
   PRIMARY KEY(idCustomer),
   UNIQUE(IdUser),
   FOREIGN KEY(idCustomerOrder) REFERENCES customerOrder(idCustomerOrder),
   FOREIGN KEY(IdUser) REFERENCES user_(IdUser)
);

CREATE TABLE CONTIENT(
   idArticle INT,
   idCustomerOrder INT,
   unitaryPrice REAL NOT NULL DEFAULT 0,
   quantity REAL NOT NULL DEFAULT 1,
   PRIMARY KEY(idArticle, idCustomerOrder),
   FOREIGN KEY(idArticle) REFERENCES article(idArticle),
   FOREIGN KEY(idCustomerOrder) REFERENCES customerOrder(idCustomerOrder)
);
