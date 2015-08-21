INSERT INTO Item SELECT *
     FROM (
            SELECT 1, 'drink', 'Gatorade', 1.5, 5 UNION
            SELECT 2, 'drink', 'Water', 1.5, 5 UNION
            SELECT 3, 'drug', 'Monster Energy Drink', 4.5, 10 UNION
            SELECT 4, 'lunch', 'Chicken-Cheese Sandwich', 3.0, 8 UNION
            SELECT 5, 'chips', 'Nachos', 3.0, 18 UNION
            SELECT 6, 'medicine', 'Jack Daniels', 20.0, 7) item
     WHERE NOT EXISTS (SELECT * FROM Item);

COMMIT;