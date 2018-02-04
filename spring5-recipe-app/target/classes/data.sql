-- unit_of_measure table name corresponds to the java class UnitOfMeasure.
-- category table name corresponds to the java class Category
-- description column name refers to the field name description in java class Category
-- uom column name refers to the field name description in java class UnitOfMeasure
-- h2 has a convention of separating words by underscore.
-- Also java class camel name convention is converted to all small letters in h2 like in all rational databases.
-- Like most rational databases, table and column names are case insensitive and it is advisable to not make your database case sensitive.
-- To see how your tables and columns are named, check for instance the h2 database. You will see UNIT_OF_MEASURE for example.
INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');
INSERT INTO unit_of_measure (uom) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (uom) VALUES ('Tablespoon');
INSERT INTO unit_of_measure (uom) VALUES ('Cup');
INSERT INTO unit_of_measure (uom) VALUES ('Pinch');
INSERT INTO unit_of_measure (uom) VALUES ('Ounce');
INSERT INTO unit_of_measure (uom) VALUES ('Dash');
INSERT INTO unit_of_measure (uom) VALUES ('Pint');
INSERT INTO unit_of_measure (uom) VALUES ('Each');
