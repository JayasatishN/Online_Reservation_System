# Online Reservation System

## Project Overview
The **Online Reservation System** is a web-based application designed to facilitate online bookings and cancellations. It allows users to register, log in, and manage their reservations efficiently.

## Features
- User registration and authentication
- Booking system for reservations
- Cancellation of existing reservations
- Database integration using MySQL
- Backend powered by Java Servlets and JDBC

## Technologies Used
- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java, Servlets, JDBC
- **Database:** MySQL
- **Version Control:** Git, GitHub

## Installation and Setup
### Prerequisites:
- Install **Java JDK**
- Install **Apache Tomcat**
- Install **MySQL Database**
- Install **Eclipse IDE** (or any Java-supported IDE)

### Steps to Run the Project:
1. Clone the repository:
   ```bash
   git clone https://github.com/JayasatishN/Online_Reservation_System.git
   ```
2. Open the project in **Eclipse IDE**.
3. Configure the **Apache Tomcat Server**.
4. Create a MySQL database and import the `database.sql` file:
   ```sql
   source database.sql;
   ```
5. Run the project on the server.
6. Access the application through your browser.

## Database Schema
The project includes a MySQL database with tables for users, reservations, and booking details. The `database.sql` file contains all necessary queries for setup.

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m "Added new feature"`)
4. Push to the branch (`git push origin feature-branch`)
5. Open a Pull Request

## License
This project is open-source and available under the **MIT License**.
