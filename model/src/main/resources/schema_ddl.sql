DROP TABLE IF EXISTS Item;
create table Item (id bigint not null, name varchar(255), price float, quantity integer, type varchar(255), primary key (id));

DROP SEQUENCE IF EXISTS hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
