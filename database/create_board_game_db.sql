-- Create database
CREATE DATABASE boardGameManager_dev;

-- Connect to the newly created database
\c boardGameManager_dev;

-- Create table board_game
CREATE TABLE board_game (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    description TEXT
);
