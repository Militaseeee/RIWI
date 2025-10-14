# LibroNova - Library Management System

LibroNova is a desktop-based library management system built with Java SE. This application serves as an internal tool for librarians to manage books, members, and loans, while also providing a self-service portal for library members. This project was developed as the performance assessment for the Java Module 5.1.

---

## 🚀 Features

* **Role-Based Access Control**: Separate functionalities and menus for Librarians (Users) and Members.
* **Book Management (Admin)**: Full CRUD operations for the book catalog (Create, Read, Update, Delete).
* **Member Management (Admin)**: Register new members and view the list of all existing members.
* **Loan Management**:
    * Librarian-Assisted Loans: Librarians can create and return loans on behalf of members.
    * Self-Service Loans: Members can borrow books directly through their personal portal.
    * Automatic Fine Calculation: The system automatically calculates fines for overdue returns.
* **Transactional Integrity**: Critical operations like creating or returning a loan are handled atomically to ensure data consistency (e.g., updating book stock and loan status together or not at all).
* **Reporting**: Export funcionality to generate CSV reports for the book catalog and overdue loans.
* **External Configuration**: Database credentials and business parameters are managed externally in a `config.properties` file.

---

## 🏗️ Architecture

The project is built following a clean, layered architecture to ensure separation of concerns, maintainability, and testability.

* **Model (`domain`)**: Contains the POJO (Plain Old Java Object) classes that represent the application's entities (e.g., `Book`, `Loan`, `Member`).
* **DAO - Data Access Object (`dao`)**: This layer is responsible for all database interactions. It abstracts the persistence logic using JDBC and `PreparedStatement` to communicate with the PostgreSQL database.
* **Service (`service`)**: Contains the core business logic, validations (e.g., checking book stock, member status), and transaction management. It acts as a bridge between the Controller and DAO layers.
* **Controller (`controller`)**: A thin layer that receives requests from the View and delegates them to the appropriate Service. It contains no business or presentation logic.
* **View (`view`)**: The presentation layer, responsible for all user interaction. It is built entirely with `JOptionPane` dialogs.

---

## 🛠️ Tech Stack & Tools

* **Language**: Java 17
* **Build Tool**: Apache Maven
* **Database**: PostgreSQL
* **Testing**: JUnit 5
* **IDE**: IntelliJ IDEA / NetBeans

---

## ⚙️ Prerequisites & Setup

Before running the application, please ensure you have the following installed:

* JDK 17 or higher.
* Apache Maven.
* A running instance of PostgreSQL.

### Installation Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-github-username/your-repository-name.git](https://github.com/your-github-username/your-repository-name.git)
    cd your-repository-name
    ```

2.  **Database Setup:**
    * Create a new database in PostgreSQL (e.g., `libronova_db`).
    * Execute the `database.sql` script provided below to create all necessary tables and initial data (roles and statuses).

    <details>
    <summary>Click to view database.sql script</summary>

    ```sql
    -- Drop tables if they exist to start fresh
    DROP TABLE IF EXISTS loan, loan_status, member, users, role, book CASCADE;

    -- Create Tables
    CREATE TABLE book (
      id_book SERIAL PRIMARY KEY,
      title VARCHAR(100) NOT NULL,
      author VARCHAR(100) NOT NULL,
      isbn VARCHAR(100) UNIQUE NOT NULL,
      stock INT NOT NULL,
      status BOOLEAN DEFAULT TRUE
    );

    CREATE TABLE role (
      id_role SERIAL PRIMARY KEY,
      name VARCHAR(20) NOT NULL UNIQUE
    );

    CREATE TABLE users (
      id_user SERIAL PRIMARY KEY,
      id_role INT NOT NULL,
      username VARCHAR(100) NOT NULL UNIQUE,
      email VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(100) NOT NULL,
      status BOOLEAN DEFAULT TRUE,
      FOREIGN KEY (id_role) REFERENCES role(id_role)
    );

    CREATE TABLE member (
      id_member SERIAL PRIMARY KEY,
      full_name VARCHAR(250) NOT NULL,
      email VARCHAR(100) NOT NULL UNIQUE,
      phone VARCHAR(100) NOT NULL,
      active BOOLEAN DEFAULT TRUE
    );

    CREATE TABLE loan_status (
      id_status SERIAL PRIMARY KEY,
      name VARCHAR(20) NOT NULL UNIQUE
    );

    CREATE TABLE loan (
      id_loan SERIAL PRIMARY KEY,
      id_user INT NOT NULL,
      id_book INT NOT NULL,
      id_member INT NOT NULL,
      id_status INT NOT NULL,
      loan_date DATE NOT NULL,
      return_date DATE NOT NULL,
      actual_return_date DATE,
      fine NUMERIC(10,2) DEFAULT 0,
      FOREIGN KEY (id_user) REFERENCES users (id_user),
      FOREIGN KEY (id_book) REFERENCES book (id_book),
      FOREIGN KEY (id_member) REFERENCES member (id_member),
      FOREIGN KEY (id_status) REFERENCES loan_status (id_status)
    );

    -- Insert Initial Data
    INSERT INTO loan_status (name) VALUES ('Borrowed'), ('Returned'), ('Late');
    INSERT INTO role (name) VALUES ('Admin'), ('Assistant');

    -- Insert a default admin user for testing
    INSERT INTO users (id_role, username, email, password, status) VALUES
    (1, 'admin', 'admin@libronova.com', 'admin123*', true);
    ```
    </details>

3.  **Configure Application:**
    * Open the file `src/main/resources/config.properties`.
    * Update the `db.url`, `db.user`, and `db.password` properties to match your database configuration.

    ```properties
    # Database Connection Configuration
    db.url=jdbc:postgresql://localhost:5432/libronova_db
    db.user=your_postgres_user
    db.password=your_secret_password
    ```

4.  **Build and Run:**
    * Open a terminal in the project root and build the project using Maven:
        ```bash
        mvn clean install
        ```
    * Run the application by executing the `main` method in the `app.Main` class from your IDE.

---

## 👨‍💻 Usage

1.  Upon launching, the application will present a main menu asking you to identify as a **Librarian (User)** or a **Member**.

2.  **Librarian Flow**:
    * Select "Login as Librarian".
    * Use the default credentials:
        * **Username**: `admin`
        * **Password**: `admin123*`
    * You will be granted access to the Admin Panel with full permissions to manage the library.

3.  **Member Flow**:
    * Select "Login as Member".
    * You will see a portal with two options:
        1.  **Login with Email**: If you are already registered, enter your email to access your personal menu.
        2.  **Create New Account**: If you are a new member, you can register here.
    * Once logged in, you can view the book catalog and borrow books.

---

## 📸 Screenshots

*(INSTRUCTION: Take screenshots of your application and place them in a folder like `/screenshots` in your project. Then, update the paths below.)*

**Main Menu**
![Main Menu](path/to/your/main_menu_screenshot.png)

**Admin Panel**
![Admin Panel](path/to/your/admin_panel_screenshot.png)

**Member Portal**
![Member Portal](path/to/your/member_portal_screenshot.png)

---