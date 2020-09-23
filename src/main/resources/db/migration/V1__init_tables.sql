CREATE TABLE administrators(
ADMINISTRATOR_ID INT PRIMARY KEY AUTO_INCREMENT,
FIRST_NAME VARCHAR(30),
LAST_NAME VARCHAR(30),
DATE_OF_BIRTH DATE,
ADDRESS VARCHAR(30),
PHONE_NUMBER BIGINT,
EMAIL VARCHAR(30)
);

CREATE TABLE waiters(
WAITER_ID INT PRIMARY KEY AUTO_INCREMENT,
FIRST_NAME VARCHAR(30),
LAST_NAME VARCHAR(30),
DATE_OF_BIRTH DATE,
ADDRESS VARCHAR(30),
PHONE_NUMBER BIGINT,
EMAIL VARCHAR(30)
);

CREATE TABLE menus(
PRODUCT_ID INT PRIMARY KEY AUTO_INCREMENT,
CATEGORY VARCHAR(30),
NAME VARCHAR(30),
DESCRIPTION VARCHAR(100),
PRICE DECIMAL(19,2)
);

CREATE TABLE tables(
TABLE_ID INT PRIMARY KEY AUTO_INCREMENT,
NUMBER INT
);

CREATE TABLE orders(
ORDER_ID INT PRIMARY KEY AUTO_INCREMENT,
ORDER_NUMBER INT,
QUANTITY INT,
PRODUCT_ID INT,
TABLE_ID INT
);

CREATE TABLE checkout(
CHECK_OUT_ID INT PRIMARY KEY AUTO_INCREMENT,
ORDER_ID INT,
PAYMENT_TYPE VARCHAR(30)
);

-- ALTER TABLE menus
-- ADD CONSTRAINT MENU_ORDERS_FK FOREIGN KEY (ORDER_ID)
-- references orders(ORDER_ID)

ALTER TABLE orders
ADD CONSTRAINT ORDER_MENUS_FK FOREIGN KEY (PRODUCT_ID)
REFERENCES menus(PRODUCT_ID);

ALTER TABLE orders
ADD CONSTRAINT ORDER_TABLES_FK FOREIGN KEY (TABLE_ID)
REFERENCES tables(TABLE_ID);

ALTER TABLE checkout
ADD CONSTRAINT CHECKOUT_ORDER_FK FOREIGN KEY (ORDER_ID)
REFERENCES orders(ORDER_ID);