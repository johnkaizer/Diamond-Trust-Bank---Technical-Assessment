# Banking Platform Microservices  - JOHN KAISER

A modular microservices-based banking platform built using **Spring Boot** and **Java 17**, managing customers, accounts, and card services independently.

---

## 📁 Project Structure
```
banking-platform/
├── customer-service/     # Manages customer data
├── account-service/      # Manages account data
├── card-service/         # Manages card data with masking logic
└── pom.xml               # Parent Maven POM
```

---

## ⚙️ Technologies
- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Maven (multi-module)
- JUnit 5

---

## 🚀 How to Run the Application

### 🔧 Prerequisites
- JDK 17+
- Maven
- PostgreSQL installed and running locally

### 📦 1. Clone the Repository
```bash
git clone https://github.com/johnkaizer/Diamond-Trust-Bank---Technical-Assessment.git
cd banking-platform
```

### 🐘 2. Setup PostgreSQL Databases Manually
Create the following databases in your local PostgreSQL setup:
- `customer_db`
- `account_db`
- `card_db`

Use a PostgreSQL client (e.g., pgAdmin or CLI):
```sql
CREATE DATABASE customer_db;
CREATE DATABASE account_db;
CREATE DATABASE card_db;
```

### ⚙️ 3. Configure Each Service's `application.properties`

#### customer-service/src/main/resources/application.properties
```properties
server.port=4001
spring.datasource.url=jdbc:postgresql://localhost:5432/customer_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update
```

#### account-service/src/main/resources/application.properties
```properties
server.port=4002
spring.datasource.url=jdbc:postgresql://localhost:5432/account_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update
```

#### card-service/src/main/resources/application.properties
```properties
server.port=4003
spring.datasource.url=jdbc:postgresql://localhost:5432/card_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update
```

### ▶️ 4. Build and Run
```bash
# From root directory
mvn clean install

# Then run each module individually:
cd customer-service && mvn spring-boot:run
cd account-service && mvn spring-boot:run
cd card-service && mvn spring-boot:run
```


---

## 📬 API Endpoints
Each service runs independently:

| Service            | Port | Base URL           |
|--------------------|------|--------------------|
| Customer Service   | 4001 | `/api/customers`   |
| Account Service    | 4002 | `/api/accounts`    |
| Card Service       | 4003 | `/api/cards`       

---

## 🧪 Testing
Run unit tests using:
```bash
mvn test
```

---

## 📌 Notes
- Cards support masking PAN and CVV by default.
- Each account can have max 2 cards, one of each type.
- Services are decoupled for easy scaling and future intercommunication (via REST or messaging).

---

## 🧑‍💻 Author
**John Kaiser [@johnkaizer]**

---

## 📄 License
This project is licensed under the MIT License.
