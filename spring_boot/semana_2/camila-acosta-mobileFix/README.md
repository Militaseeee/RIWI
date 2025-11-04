# 📱 MobileFix - Repair Order Management

`MobileFix` is a Full-Stack web application built with Spring Boot and Java 21, designed to manage mobile device repair orders. The application implements a layered architecture and supports three distinct user roles (ADMIN, TECH, USER) with specific permissions.

This project serves as a practical exercise to demonstrate a complete development workflow, including business logic in the service layer, persistence with Spring Data JPA, dynamic views with JSP, and unit testing with JUnit 5 and Mockito.

## ✨ Key Features

* **Role-Based Authentication:** A custom login system (`/api/auth/login`) validates user credentials against the H2 database.
* **Dynamic Views:** Separate JSP dashboards for each role, which consume an internal REST API using `fetch`.
* **Order Management (Status Flow):**
    * **USER:** Can create new repair requests and cancel their own orders if they are `PENDING`.
    * **TECH:** Can view their assigned orders and update the status (from `PENDING` -> `IN_PROGRESS` -> `READY` -> `DELIVERED`).
    * **ADMIN:** Has full control. Can assign technicians, cancel any order, and manage device and user catalogs.
* **Error Handling:** Standardized JSON error responses (400, 403, 404, 409) using `@RestControllerAdvice`.
* **Unit Tests:** Business logic is validated with JUnit 5 and Mockito for the service layer.

## 🛠️ Tech Stack

* **Backend:** Java 21, Spring Boot 3 (Web, Data JPA, Validation)
* **Database:** H2 (In-Memory)
* **Persistence:** Spring Data JPA (Hibernate)
* **Frontend:** JSP (JavaServer Pages) & JavaScript (fetch)
* **Testing:** JUnit 5 & Mockito
* **Dependency Management:** Maven

---

## 🚀 Getting Started

### Prerequisites

* JDK 21 (or higher)
* Apache Maven 3.8 (or higher)
* An IDE (like IntelliJ IDEA or VSCode)

### Installation and Execution

1.  **Clone the repository**
    ```sh
    git clone [https://github.com/your-username/camila-acosta-mobileFix.git](https://github.com/your-username/camila-acosta-mobileFix.git)
    cd camila-acosta-mobileFix
    ```

2.  **Run the application**
    * **From an IDE:** Open the project, locate the `CamilaAcostaMobileFixApplication.java` class, and run it.
    * **From the terminal:**
        ```sh
        mvn spring-boot:run
        ```

3.  **Access the application**
    The application will be available at `http://localhost:8080`.

---

## 🔑 Access & Test Users

The application initializes with an in-memory H2 database, which is pre-populated with test data from `src/main/resources/data.sql`.

**1. Login Page:**
Access the application via the login page:
`http://localhost:8080/login`

**2. Test Credentials:**
You can use the following users:

| Role | Username | Password | ID (in DB) |
| :--- | :--- | :--- | :--- |
| 👑 **ADMIN** | `admin` | `pass` | 1 |
| 👑 **ADMIN** | `cami_admin` | `1234` | 2 |
| 👨‍🔧 **TECH** | `tech1` | `pass` | 10 |
| 👨‍🔧 **TECH** | `tech2` | `pass` | 11 |
| 👤 **USER** | `user1` | `pass` | 100 |
| 👤 **USER** | `user2` | `pass` | 101 |

### 📁 H2 Console Access

You can inspect the database in real-time while the application is running.
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:mobilefixdb`
* **Username:** `camila`
* **Password:** (leave blank)

---

## 📐 Architecture

The project follows a layered architecture for separation of concerns:

```
com.crudActivity.camila_acosta_mobileFix
├── 📁 controller/   (Endpoints REST, do HTTP)
│   ├── AuthController.java
│   ├── DeviceController.java
│   ├── RepairOrderController.java
│   ├── UserController.java
│   └── ViewController.java         (Have los JSPs)
├── 📁 dto/          (Data Transfer Objects for API)
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   └── ...
├── 📁 exception/    (Custom exceptions y GlobalExceptionHandler)
├── 📁 model/        (Entities JPA: User, Device, RepairOrder)
├── 📁 repository/   (Interfaces the Spring Data JPA)
├── 📁 service/      (Business logic)
│   ├── RepairOrderService.java
│   ├── UserService.java
│   └── impl/       (Implementations of the logic)
├── 📁 resources/
│   ├── application.properties
│   └── data.sql    (Initial test data)
└── 📁 webapp/WEB-INF/jsp/ (Vistas JSP)
├── login.jsp
├── admin_dashboard.jsp
├── tech_dashboard.jsp
└── user_dashboard.jsp
```

## 🗺️ API REST Endpoints

* `POST /api/auth/login`: Authenticates a user and returns their ID and Role.

### Repair Orders
* `GET /api/orders`: Gets all orders.
* `POST /api/orders/{customerId}`: (USER) Creates a new repair order.
* `PUT /api/orders/{id}/assign/{techId}/{adminActorId}`: (ADMIN) Assigns a technician to an order.
* `PUT /api/orders/{id}/status/{actorId}`: (TECH/ADMIN) Changes an order's status.
* `DELETE /api/orders/{id}/cancel/{actorId}`: (USER/ADMIN) Cancels an order.

### Devices & Users (Admin)
* `GET /api/devices`: Gets all devices.
* `POST /api/devices`: (ADMIN) Creates a new device.
* `GET /api/users`: (ADMIN) Gets all users.
* `POST /api/users`: (ADMIN) Creates a new user (User, Tech, or Admin).

---

## 🧪 Unit Tests

Unit tests are located in `src/test/java` and use **JUnit 5** and **Mockito** to test the service layer (`RepairOrderServiceImpl`) in isolation.

The tests cover the critical business rules defined in the workshop:
* **`createOrder`**: Validates creation with `PENDING` status and fails if the description is too short or the device doesn't exist.
* **`assignTech`**: Validates that only an `ADMIN` can assign technicians.
* **`changeStatus`**: Validates the status flow (e.g., `PENDING` can only go to `IN_PROGRESS`).
* **`deleteOrder`**: Validates that a `USER` can only cancel their own orders if they are `PENDING`.

### How to Run Tests

You can run the tests from your IDE (right-click on `RepairOrderServiceImplTest.java` > Run) or by using Maven:

```sh
mvn test
```

---

## -> Autor

- Camila Acosta 