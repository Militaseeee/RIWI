# 📝 TinyTasks — CRUD Activity (Spring Boot + Vite)

TinyTasks is a small full-stack application designed to practice building a minimal Spring Boot backend and a native JavaScript frontend using Vite.
It allows users to manage a simple task list — create, list, toggle completion, and delete — all stored in memory (no database required).

---

## 🚀 Overview

This project demonstrates a complete **flow:**

**Frontend (Vite + Vanilla JS)** → **API (Spring Boot)** → **Service Layer** → **Repository (In-memory storage)**

Each layer has a clear responsibility and can be tested independently.

---

## 🧰 Tech Stack

| Layer        | Technology                               |
| ------------ | ---------------------------------------- |
| Backend      | Java 21 + Spring Boot                    |
| Frontend     | HTML, JavaScript, Bootstrap, Vite        |
| Persistence  | In-memory list (no DB, no H2)            |
| Testing      | JUnit 5                                  |
| Naming       | All in English                           |
| Backend URL  | `http://localhost:8080/api/task`         |
| Frontend URL | `http://localhost:5173` (served by Vite) |

---

## ⚙️ Backend Structure

```
backend/
 └─ src/main/java/com/TinyTasks/TinyTasks/
      ├─ config/
      │    └─ DbConfig.java
      ├─ controller/
      │    └─ TaskController.java
      ├─ entity/
      │    └─ Task.java
      ├─ repository/
      │    └─ TaskRepository.java
      ├─ service/
      │    └─ TaskService.java
      └─ TinyTasksApplication
```

---

## ✅ API Endpoints

| Method     | Endpoint                | Description                   |
| ---------- | ----------------------- | ----------------------------- |
| **GET**    | `/api/task`             | Returns all tasks             |
| **POST**   | `/api/task`             | Creates a new task            |
| **PUT**    | `/api/task/{id}/toggle` | Toggles the completion status |
| **DELETE** | `/api/task/{id}`        | Deletes a task by ID          |

### Example Response

```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "done": false
}
```

### Example Errors

| Code | Example                            |
| ---- | ---------------------------------- |
| 400  | `{ "error": "Title is required" }` |
| 404  | `{ "error": "Not found" }`         |

---

## 💻 Frontend Setup (Vite)

```
frontend/
 ├─ index.html
 ├─ app.js
 ├─ style.css (optional)
 └─ package.json
```

### ▶️ Run Frontend

1. Open the `frontend/` folder in your terminal.

2. Initialize and install Vite:

   ```bash
   npm init -y
   npm install vite -D
   npm run dev
   ```

3. The terminal will display a local URL, usually `http://localhost:5173`.

4. Open that URL in your browser and ensure the backend is running on `http://localhost:8080`.

---

## 🔌 CORS Configuration

CORS is enabled to allow communication between the frontend (`5173`) and backend (`8080`):

```java
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

---

## 🧪 Unit Tests (JUnit 5)

| Module         | Test Case     | Positive Scenario            | Negative Scenario                          |
| -------------- | ------------- | ---------------------------- | ------------------------------------------ |
| **Service**    | Create Task   | Valid title creates a task   | Invalid/short title throws exception       |
| **Service**    | Toggle Task   | Changes `done` true/false    | Invalid ID throws `NoSuchElementException` |
| **Service**    | Delete Task   | Removes element successfully | Invalid ID throws `NoSuchElementException` |
| **Repository** | ID Generation | IDs are auto-incremental     | —                                          |
| **Repository** | Find by ID    | Returns Optional<Task>       | Returns empty Optional                     |

---

## 📋 User Stories

| ID    | User Story  | Acceptance Criteria                                                  |
| ----- | ----------- | -------------------------------------------------------------------- |
| HU-01 | List Tasks  | User can see all tasks via `GET /api/task`                           |
| HU-02 | Create Task | User can add a task via `POST /api/task`                             |
| HU-03 | Toggle Task | User can mark/unmark task completion via `PUT /api/task/{id}/toggle` |
| HU-04 | Delete Task | User can delete a task via `DELETE /api/task/{id}`                   |
| HU-05 | Unit Tests  | JUnit tests validate core business logic                             |

---

## 🧠 Key Learning Objectives

* Understand the **Spring Boot MVC pattern**: Controller → Service → Repository.
* Manage in-memory data using collections.
* Implement **CORS** for front-back communication.
* Create a minimal **REST API** and connect it to a JavaScript frontend.
* Write and execute **JUnit tests** for business logic validation.

---

## 👩‍💻 Author

**Camila Acosta**
Developed as part of the **Crudzaso TinyTasks** challenge.
🎯 Focus: clarity, coherence, and functionality.
