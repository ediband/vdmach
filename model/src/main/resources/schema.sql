CREATE TABLE IF NOT EXISTS Item (id bigint not null, name varchar(255), price float, quantity integer, type varchar(255), primary key (id));

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start with 7 increment by 1;
