CREATE DATABASE reservation_system;


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    trainNumber VARCHAR(20) NOT NULL,
    classType VARCHAR(20) NOT NULL,
    dateOfJourney DATE NOT NULL,
    fromLocation VARCHAR(50) NOT NULL,
    toLocation VARCHAR(50) NOT NULL,
    pnr VARCHAR(10) NOT NULL UNIQUE,
    FOREIGN KEY (userId) REFERENCES users(id)
);
CREATE TABLE Cancellations ( id INT AUTO_INCREMENT PRIMARY KEY, 
			reservation_id INT, 
			cancellation_date DATE, 
			FOREIGN KEY (reservation_id) REFERENCES Reservations(id) );