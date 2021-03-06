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

CREATE TABLE tables(
ID INT PRIMARY KEY AUTO_INCREMENT,
NUMBER INT,
ORDERS_ID INT,
CONSTRAINT TABLE_ORDERS_FK FOREIGN  KEY (ORDERS_ID)REFERENCES orders(ID)
);

CREATE TABLE checkout(
ID INT PRIMARY KEY AUTO_INCREMENT,
PAYMENT_TYPE VARCHAR(30),
ORDERS_ID INT ,
CONSTRAINT CHECKOUT_ORDERS_FK FOREIGN KEY (ORDERS_ID)REFERENCES orders(ID)
);

create table users
(
	id int auto_increment,
	email varchar(255) not null,
	first_name varchar(55) null,
	last_name varchar(55) null,
	password varchar(255) null,
	role varchar(55) default 'USER' null,
	status varchar(55) default 'ACTIVE' null,
	constraint users_pk
		primary key (id)
);

create unique index users_email_uindex
	on users (email);

Insert INTO users values (1,'admin@mail.com','Alex','Rock','$2y$12$3hDlVfh2j6W/OqHUWSkyTeu1NRLn/E.q/3J8HeM6NTAAOuNdNHdQ6','ADMIN','ACTIVE');
Insert INTO users values (2,'user@mail.com','Arthur','Rock','$2y$12$eusZxiNmmXAkT.xozsJituoNTjvEo989OAAqci7vgFDhn7m5n4WG6','USER','ACTIVE');