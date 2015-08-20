MERGE INTO Item (id, type, name, price, quantity) VALUES (1, 'drink', 'Gatorade', 1.5, 5);
MERGE INTO Item (id, type, name, price, quantity) VALUES (2, 'drink', 'Water', 1.5, 5);
MERGE INTO Item (id, type, name, price, quantity) VALUES (3, 'drug', 'Monster Energy Drink', 1.5, 10);
MERGE INTO Item (id, type, name, price, quantity) VALUES (4, 'lunch', 'Chicken-Cheese Sandwich', 3.0, 8);
MERGE INTO Item (id, type, name, price, quantity) VALUES (5, 'chips', 'Nachos', 3.0, 18);
MERGE INTO Item (id, type, name, price, quantity) VALUES (6, 'medicine', 'Jack Daniels', 20.0, 7);

commit;