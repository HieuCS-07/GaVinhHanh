# GaVinhHanh — Railway Ticketing & Station Management

A Java desktop application project for railway ticket booking and station operations.

## Repository overview

The codebase is organized into:
- `src/entity/` — domain models for trains, trips, carriages, seats, tickets, customers, staff, shifts, invoices, and promotions.
- `src/dao/` — data-access classes for core railway entities.
- `src/guiNhanVienBanVe/` — ticket-sales interfaces, including booking forms, seat maps, and ticket views.
- `src/guiNhanVIenQuanLy/` — management-facing interfaces.
- `src/connectDB/` — database connection code.
- `database/GaVinhHanh.sql` — SQL database script.
- `libs/` and `resources/` — project dependencies and supporting resources.

## Technical focus

- Java desktop application and UI development
- Relational data modeling and SQL integration
- Separation of data models, DAO components, and user-interface logic
- Railway seat selection and ticket booking workflows

## Getting started

This repository contains an IDE-style Java project. Review the database script and `src/connectDB/ConnectDB.java` for connection settings before attempting to run it. External libraries may need to be configured in your Java IDE.

> **Note:** This is a project repository. A one-command setup, automated deployment, and production availability have not been verified.

## Author

[HieuCS-07](https://github.com/HieuCS-07)
