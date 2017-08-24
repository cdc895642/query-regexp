DROP SCHEMA IF EXISTS querytest;
CREATE SCHEMA querytest CHARACTER SET UTF8;
USE querytest;

CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON querytest.* TO 'test'@'localhost';

FLUSH PRIVILEGES ;