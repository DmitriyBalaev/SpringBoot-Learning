DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  balance INT NULL
);
INSERT Into accounts (balance) VALUES
    (1000),
    (2000),
    (3000);

