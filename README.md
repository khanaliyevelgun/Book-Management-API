# 📚 Book Reservation Application

A RESTful API for managing book reservations in a library system. Built with Spring Boot, secured with JWT authentication, and containerized with Docker.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **PostgreSQL**
- **Liquibase** — database migrations
- **Docker + Docker Compose**
- **Swagger / OpenAPI** — API documentation
- **Lombok**
- **Gradle**

---

## 📐 Architecture

The project follows a layered architecture:

```
Controller → Service → Repository → Database
```

- **Controller** — handles HTTP requests and responses
- **Service** — contains business logic
- **Repository** — data access layer
- **Entity** — JPA mapped database tables
- **DTO** — data transfer objects for request/response
- **Mapper** — maps between entities and DTOs
- **Security** — JWT filter, authentication, authorization
- **Scheduler** — background jobs

---

## ⚙️ Business Rules

- User must be **ACTIVE** to create a reservation
- A user can reserve a **maximum of 3 books** at a time
- Only **AVAILABLE** books can be reserved
- When a reservation is created → status is **PENDING**
- **Admin must approve** reservations → status becomes **ACTIVE**
- Reserved books are marked as **UNAVAILABLE** automatically
- Reservations **not approved within 2 hours** are automatically cancelled by a scheduler
- Cancelled reservations restore book availability automatically
- Soft delete for both users and books (no hard deletes)

---

## 🔐 Security

JWT-based authentication with role-based access control:

| Role | Permissions |
|------|-------------|
| `USER` | Register, login, create/cancel reservations, view books |
| `ADMIN` | All USER permissions + manage books, approve reservations |

**Public endpoints:**
- `POST /api/v1/users` — register
- `POST /api/v1/auth/login` — login

---

## 🗄️ Database Schema

- **books** — book information and availability
- **users** — user accounts with roles
- **reservations** — reservation records with status
- **reservation_books** — join table for ManyToMany relationship

---

## 📦 Project Structure

```
src/main/java/com/elgun
├── auth              # Authentication (login DTO)
├── config            # Security and Swagger configuration
├── constants         # Application constants
├── controller        # REST controllers
├── dao
│   ├── entity        # JPA entities
│   └── repository    # Spring Data repositories
├── dto               # Request and response DTOs
├── enumm             # Enums (BookAvailability, ReservationStatus, etc.)
├── exception         # Custom exceptions and global handler
├── fetcher           # Entity fetch helper
├── mapper            # Entity ↔ DTO mappers
├── scheduler         # Auto-cancellation scheduler
├── security          # JWT filter, JwtUtil, UserDetailsService
└── service           # Business logic (interfaces + implementations)
```

---

## 🐳 Running with Docker

**Prerequisites:** Docker and Docker Compose installed.

```bash
# Clone the repository
git clone https://github.com/khanaliyevelgun/Book-Management-API.git
# Navigate to project directory
cd book-reservation-application

# Build and run with Docker Compose
docker-compose up --build
```

The application will be available at `http://localhost:8080`

---

## 📖 API Documentation

After running the application, visit:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔑 Authentication Flow

```
1. Register   → POST /api/v1/users
2. Login      → POST /api/v1/auth/login  → returns JWT token
3. Use token  → Add header: Authorization: Bearer <token>
```

---

## 📋 API Endpoints

### Auth
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/v1/auth/login` | Login and get JWT token | Public |

### Users
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/v1/users` | Create user | Public |
| GET | `/api/v1/users/{id}` | Get user by ID | Authenticated |
| GET | `/api/v1/users` | Get all users | Authenticated |
| PATCH | `/api/v1/users/{id}` | Update user | Authenticated |
| DELETE | `/api/v1/users/{id}` | Deactivate user | Authenticated |

### Books
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/v1/books` | Add book | Admin |
| GET | `/api/v1/books/{id}` | Get book by ID | Authenticated |
| GET | `/api/v1/books` | Get all books | Authenticated |
| PATCH | `/api/v1/books/{id}` | Update book | Admin |
| DELETE | `/api/v1/books/{id}` | Deactivate book | Admin |

### Reservations
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/v1/reservations` | Create reservation | Authenticated |
| GET | `/api/v1/reservations/{id}` | Get reservation by ID | Authenticated |
| GET | `/api/v1/reservations/user/{userId}` | Get user's reservations | Authenticated |
| PATCH | `/api/v1/reservations/{id}/approve` | Approve reservation | Admin |
| PATCH | `/api/v1/reservations/{id}/cancel` | Cancel reservation | Authenticated |

---

## 👤 Author

**Elgun** — [GitHub](https://github.com/khanaliyevelgun) • [Email](mailto:khanaliyav.elgun@gmail.com)