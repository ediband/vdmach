DROP TABLE Item IF EXISTS;
create table Item (id bigint not null, name varchar(255), price float, quantity integer, type varchar(255), primary key (id));

DROP SEQUENCE hibernate_sequence IF EXISTS;
create sequence hibernate_sequence start with 1 increment by 1;

INSERT INTO Item (id, type, name, price, quantity) VALUES (NEXT VALUE FOR hibernate_sequence, 'drink', 'Gatorade', 1.5, 5);
INSERT INTO Item (id, type, name, price, quantity) VALUES (NEXT VALUE FOR hibernate_sequence, 'drink', 'Water', 1.5, 5);
INSERT INTO Item (id, type, name, price, quantity) VALUES (NEXT VALUE FOR hibernate_sequence, 'drug', 'Monster Energy Drink', 1.5, 10);
INSERT INTO Item (id, type, name, price, quantity) VALUES (NEXT VALUE FOR hibernate_sequence, 'lunch', 'Chicken-Cheese Sandwich', 3.0, 8);
INSERT INTO Item (id, type, name, price, quantity) VALUES (NEXT VALUE FOR hibernate_sequence, 'chips', 'Nachos', 3.0, 18);
INSERT INTO Item (id, type, name, price, quantity) VALUES (NEXT VALUE FOR hibernate_sequence, 'medicine', 'Jack Daniels', 20.0, 7);

commit;