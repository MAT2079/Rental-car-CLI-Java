# Rental Car System

## Overview

This project is a console-based rental car system implemented in Java. It allows administrators to manage the fleet of cars and users to rent and return cars. The system includes functionalities for user registration, login, car addition, removal, editing, and transaction management.

## Features

- User authentication for both admins and renters.
- Car management by admins: add, remove, and edit car details.
- Rent and return cars for renters.
- Display available cars and their details.
- Enum classes for standardized car attributes like color and fuel economy.

## Requirements

- Java Development Kit (JDK) 8 or higher.

## Installation

1. Ensure you have JDK 8 or higher installed on your system.
2. Clone the repository or download the source code.
3. Open the project in your preferred Java IDE.

## Usage

1. Navigate to the `rentalcar` package in your IDE.
2. Run the `RentalCar` class to start the application.
3. Follow the console prompts to interact with the system.

## Code Structure

### Main Classes

- **RentalCar**: The main class that handles the overall flow of the application, including the main menu and user interactions.
- **Car**: An abstract class representing the general attributes of a car. Subclasses include `Sedan`, `SUV`, `Pickup`, `SuperCar`, and `Coupe`.
- **User**: An abstract class representing users of the system. Subclasses include `Admin` and `Renter`.
- **Transaction**: A class representing rental transactions, including rental and return dates.

### Enum Classes

- **Colors**: Enum for car colors.
- **FuelEconomy**: Enum for fuel economy ratings.

## Functionality

### Main Menu

1. **Admin Menu**:
   - **Login**: Admins can log in using their ID and password.
   - **Add a Car**: Admins can add a new car to the fleet.
   - **Remove a Car**: Admins can remove a car from the fleet using its ID.
   - **Edit a Car**: Admins can edit car details, including both general and specific attributes.
   - **Display All Cars**: Admins can view a list of all cars in the fleet.

2. **Customer Menu**:
   - **Login**: Renters can log in using their ID and password.
   - **Create New Account**: New users can register by providing their name, phone number, and password.
   - **Rent a Car**: Renters can rent a car by selecting it from the available cars.
   - **Return a Car**: Renters can return a rented car.

### Admin Functions

- **adminLogin**: Validates admin credentials.
- **addCar**: Prompts for car details and adds a new car to the fleet.
- **removeCar**: Removes a car from the fleet by ID.
- **editCar**: Edits details of an existing car.
- **displayAdminMenu**: Displays the admin menu and handles admin actions.

### Renter Functions

- **customerLogin**: Validates renter credentials.
- **customerRegister**: Registers a new renter.
- **makeRentTransaction**: Handles the car rental process.
- **makeReturnTransaction**: Handles the car return process.
- **displayCustomerMenu**: Displays the customer menu and handles customer actions.

### Car Classes

Each car subclass extends the `Car` class and includes specific attributes and methods:
- **SUV**: Attributes like seat count, trunk size, and fuel economy.
- **Coupe**: Attributes like convertible status, max speed, and horsepower.
- **Pickup**: Attributes like trunk size, torque, and fuel economy.
- **Sedan**: Attributes like fuel economy and hatchback status.
- **SuperCar**: Attributes like acceleration, horsepower, lap time, and top speed.

## Customization

- **Car Attributes**: Modify the car subclasses to add or change attributes as needed.
- **User Management**: Extend user functionalities or create new user roles by adding subclasses to the `User` class.
- **Transaction Management**: Customize the transaction process by modifying the `Transaction` class.

## License

This project is open-source and available under the [MIT License](LICENSE).

Enjoy managing your rental car fleet and providing an excellent service to your customers!
