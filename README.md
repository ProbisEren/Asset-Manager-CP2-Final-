# 💼 Asset Manager

**Asset Manager** is a desktop asset management application developed using Java and the Swing GUI framework.  
Users can log in, view their financial assets (cryptocurrencies, stocks, currencies, precious metals, etc.), and manage their personal portfolios.

---

## 🚀 Features

- 🔐 User registration and login system (MySQL supported)
- 💰 Asset list display (Bitcoin, Euro, Tesla, Gold, etc.)
- ➕ Admin functionality to add new assets to the system
- 📊 User-specific purchase transactions, portfolio history, and wallet tracking
- 📁 File read/write operations (`dosya.txt`)
- 🧾 SQL-based database setup file (`cp2_project.sql`)
- ☕ Project configured with NetBeans and Maven

---

## 🛠️ Setup

### 1. Requirements

- Java JDK 8 or higher  
- NetBeans (or any other Java IDE)  
- MySQL Server  
- Maven  

### 2. Clone the Project

```bash
git clone [link](https://github.com/ProbisEren/Asset-Manager-CP2-Final-)
```

### 3. Database Setup

- Open `cp2_project.sql` using MySQL Workbench or the terminal and run:

```sql
SOURCE cp2_project.sql;
```

> This script creates the `users`, `valuables`, and `user_asset` tables with sample data.

### 4. Database Credentials

Create a file named `db_user_pswrd.txt` in the root project directory and write the following inside:

```
path
root
your_mysql_password
```

> This file is read by the application at runtime and is excluded from Git using `.gitignore`.

### 5. Running the Application

- Open the project using NetBeans  
- Run the project using the `login_frame` class  
- On the login screen, enter your credentials to log in

#### Sample Login Credentials

```
Username: eren
Password: erik

Username: admin
Password: admin123
```
