# EasyShop E-Commerce API

This project is a backend Spring Boot API for **EasyShop**, an online e-commerce website. The goal of the project was to extend and improve an existing e-commerce platform by fixing bugs and implementing new features for Version 2 of the site.

This capstone was completed as part of the YearUp Java Development Program.

---

## 📚 Project Overview

EasyShop is an online store allowing users to:
- browse products by category
- search and filter products
- manage a personal shopping cart
- update their user profile
- check out and place orders (planned future feature)

**Features added or fixed in this project:** 

✅ Fixed bugs in product search/filter  
✅ Resolved duplicate product issue caused by incorrect update logic  
✅ Fully implemented Shopping Cart functionality  
✅ Implemented User Profile endpoints for viewing and updating user info  
✅ Secured all sensitive endpoints with JWT authentication

---

## 🚀 How to Run the Project

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/easyshop-api.git
    cd easyshop-api
    ```

2. **Set up the database:**

    - Open MySQL Workbench
    - Run the `create_database.sql` script
    - This creates the `easyshop` database with tables and data

3. **Run the app:**

    ```bash
    mvn spring-boot:run
    ```

   Access the API:

    ```
    http://localhost:8080
    ```