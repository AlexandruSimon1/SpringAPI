# This type of implementation of My SQL is used for FlyWay in order to create automatically our data base
CREATE TABLE administrators(
ID INT PRIMARY KEY AUTO_INCREMENT,
FIRST_NAME VARCHAR(30),
LAST_NAME VARCHAR(30),
DATE_OF_BIRTH DATE,
ADDRESS VARCHAR(30),
PHONE_NUMBER BIGINT,
EMAIL VARCHAR(30)
);

CREATE TABLE waiters(
ID INT PRIMARY KEY AUTO_INCREMENT,
FIRST_NAME VARCHAR(30),
LAST_NAME VARCHAR(30),
DATE_OF_BIRTH DATE,
ADDRESS VARCHAR(30),
PHONE_NUMBER BIGINT,
EMAIL VARCHAR(30)
);

CREATE TABLE menus(
ID INT PRIMARY KEY AUTO_INCREMENT,
CATEGORY VARCHAR(30),
NAME VARCHAR(30),
DESCRIPTION VARCHAR(100),
PRICE DECIMAL(19,2)
);

CREATE TABLE orders(
ID INT PRIMARY KEY AUTO_INCREMENT,
ORDER_NUMBER INT,
MENUS_ID INT,
CONSTRAINT ORDER_MENUS_FK FOREIGN KEY (MENUS_ID)references menus(ID)
);

CREATE TABLE checkout(
ID INT PRIMARY KEY AUTO_INCREMENT,
PAYMENT_TYPE VARCHAR(30),
ORDERS_ID INT ,
CONSTRAINT CHECKOUT_ORDERS_FK FOREIGN KEY (ORDERS_ID)REFERENCES orders(ID)
);

CREATE TABLE tables(
ID INT PRIMARY KEY AUTO_INCREMENT,
NUMBER INT,
ORDERS_ID INT,
CONSTRAINT TABLE_ORDERS_FK FOREIGN  KEY (ORDERS_ID)REFERENCES orders(ID)
);