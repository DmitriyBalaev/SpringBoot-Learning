DROP TABLE IF EXISTS books;

CREATE TABLE books(
id SERIAL PRIMARY KEY,
name VARCHAR(255),
author VARCHAR(255));

INSERT INTO books (name, author) VALUES
('The Chronicles of Narnia','Clive Staples Lewis'),
('Bridge to Terabithia', 'Katherine Paterson'),
('Mary Poppins', 'Pamela Travers');