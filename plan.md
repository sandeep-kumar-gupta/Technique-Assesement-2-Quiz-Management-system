# Quiz Management System – PLAN

## 1. Objective

Build a small but production-ready **Quiz Management System** where:

- **Admin Panel (via APIs)**
  - Create a quiz with:
    - Quiz title
    - Questions of various types: MCQ, True/False, Text
- **Public API**
  - Anyone can fetch a quiz and submit answers
  - System calculates score and stores submission
  - Exposes an API to view submission result

Focus is on a **robust backend** (Spring Boot + MySQL) with **basic security** and clean separation of layers.

---

## 2. Tech Stack & Tools

- **Backend**: Spring Boot 3 (Java 17), Spring Web, Spring Data JPA
- **Database**: MySQL
- **Security**: Spring Security with Basic Auth (in-memory admin)
- **Build Tool**: Maven
- **IDE**: IntelliJ / VSCode / Eclipse
- **API Testing**: Postman
- **AI Agent**: Used for boilerplate and structure help

---

## 3. Assumptions

1. **Admin Authentication**
   - Only `/api/admin/**` endpoints are protected.
   - Admin credentials:
     - Username: `admin`
     - Password: `admin123`
   - Uses Spring Security **Basic Auth** with in-memory user.
   - Public APIs (`/api/quizzes/**`, `/api/submissions/**`) are open.

2. **Question Types**
   - Supported:
     - `MCQ` – multiple choice, exactly one option marked correct.
     - `TRUE_FALSE` – modeled as MCQ with options "true"/"false".
     - `TEXT` – free-text answer, **not auto-scored**.
   - Auto-scoring:
     - MCQ & TRUE_FALSE → auto-evaluated by comparing answer text with the correct option text.
     - TEXT → stored, but not used for score calculation.

3. **Data Model**
   - A `Quiz` has many `Question`s.
   - A `Question` may have many `Option`s (for MCQ / TRUE_FALSE).
   - A `Submission` is created per quiz attempt and stores:
     - Score
     - Total questions
     - Per-question answers.

4. **API Model**
   - We use **entities directly as JSON** 
     - Admin sends a `Quiz` structure (with nested questions/options) to create.
     - Public reads `Quiz` as-is to display.


5. **Deployment**
   - Primary deployment target is local (localhost).
   - If time permits, can be deployed to any cloud platform.
   - No CI/CD required for this assignment.

---

## 4. High-Level Architecture

```text
┌──────────────────────────────────────────┐
│          Quiz Management Backend        │
│                (Spring Boot)            │
│                                          │
│  Controllers → Services → Repositories  │
│     (REST JSON over HTTP, JPA to DB)    │
└───────────────────────▲──────────────────┘
                        │
                        │ JPA / Hibernate
                        │
┌───────────────────────┴──────────────────┐
│                MySQL Database            │
└──────────────────────────────────────────┘
