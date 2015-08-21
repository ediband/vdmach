CREATE TABLE IF NOT EXISTS Item (id bigint not null, type varchar(255), name varchar(255), price float, quantity integer, primary key (id));

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start with 7 increment by 1;
