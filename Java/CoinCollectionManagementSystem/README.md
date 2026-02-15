# Coin Collection Management System

A menu-driven Java console application designed to manage a personal coin collection with full database integration. The project demonstrates clean layered architecture (Controller → Service → DAO → Database) and practical use of JDBC with MySQL.

## Features
- **Add Coin**: Insert coins directly into the database (no separate save step).
- **Show All Coins**: Display all coins currently in memory.
- **Search Options**:
  - By country
  - By year of minting
  - By value (sorted)
  - By country + denomination
- **Load from Database**: Refresh the in-memory collection from MySQL.
- **Bulk Upload from CSV**: Import multiple coins at once.
- **Delete Coin**: Remove coins by their ID for complete CRUD functionality.
- **Exit**: Close the application safely.

## Tech Stack
- **Java** (Core + JDBC)
- **MySQL** (with auto-increment schema design)
- **DAO/Service/Controller** layered design pattern
- **CSV handling** for bulk operations

## Database Schema
```sql
CREATE TABLE coins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(50),
    denomination VARCHAR(50),
    year INT,
    value DOUBLE,
    acquired_date DATE
);

