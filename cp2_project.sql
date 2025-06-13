
CREATE DATABASE IF NOT EXISTS cp2_project;
USE cp2_project;


CREATE TABLE IF NOT EXISTS users (
    idusers INT PRIMARY KEY,
    username VARCHAR(50),
    surname VARCHAR(50),
    password VARCHAR(100),
    card_infos VARCHAR(50),
    age INT,
    balance DOUBLE
);

INSERT INTO users (idusers, username, surname, password, card_infos, age, balance) VALUES
(1, 'eren', 'uzun', 'erik', '61616161', 21, 97.66000000000349),
(2, 'admin', 'admin', 'admin123', '11111', 125, 9.633),
(3, 'emre', 'aydoğmuş', 'emre123', '389', 21, 1007.6),
(4, 'ahmet', 'Türk', '123', '456123789', 28, 10),
(5, 'Mustafa', 'Kemal', '1234', '000001', 25, 10),
(6, 'Cengiz', 'Uygar', 'c123u', '000002', 24, 10),
(7, 'ecrin', 'selen', 'esu123', '123489', 26, 10);



CREATE TABLE IF NOT EXISTS valuables (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE
);

INSERT INTO valuables (id, name, price) VALUES
(1, 'Bitcoin', 102973.497),
(2, 'Etherium', 1406.763),
(3, 'Solana', 131.67),
(4, 'Sushi', 0.568),
(5, 'Xripple', 2.81),
(6, 'Euro', 0.633),
(7, 'Turkish Lira', 0.242),
(8, 'Sterling', 0.831),
(9, 'Gold/Ons', 3937.604),
(10, 'Silver/Ons', 31.119),
(11, 'NVDA', 141.014),
(12, 'Tesla', 316);


CREATE TABLE IF NOT EXISTS user_asset (
    user_id INT,
    valuable_id INT,
    how_many INT,
    user_assetcol INT
);

INSERT INTO user_asset (user_id, valuable_id, how_many, user_assetcol) VALUES
(1, 11, 50, 20),
(1, 3, 1302, 21);
