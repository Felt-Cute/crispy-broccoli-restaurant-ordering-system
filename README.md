# Crispy Broccoli Restaurant Ordering System

[![GitHub Stars](https://img.shields.io/github/stars/felt-cute/crispy-broccoli-restaurant-ordering-system.svg)](https://github.com/felt-cute/crispy-broccoli-restaurant-ordering-system/stargazers)
[![GitHub Issues](https://img.shields.io/github/issues/felt-cute/crispy-broccoli-restaurant-ordering-system.svg)](https://github.com/felt-cute/crispy-broccoli-restaurant-ordering-system/issues)
[![Current Version](https://img.shields.io/badge/version-0.0.1-green.svg)](https://github.com/felt-cute/crispy-broccoli-restaurant-ordering-system)
[![Live Demo](https://img.shields.io/badge/demo-offline-red.svg)](https://fmhh.vercel.app)

The Restaurant Ordering System is a web-based application allowing
customers to view menus, place orders, and track order status.

It also provides restaurant staff with tools to manage orders and update menus.

---
## Key Features
1. Menu viewing
2. Order placement 
3. Order tracking 
4. User authentication 
5. Restaurant management interface 
6. Payment integration

---
## Technologies
Project is created with:
* [Java 21](https://docs.oracle.com/en/java/javase/21/)
* [Spring Boot: 3.3.1](https://docs.spring.io/spring-boot/docs/current/reference/html/)

---

## Running Locally

Clone the project

```bash
  git clone https://github.com/felt-cute/crispy-broccoli-restaurant-ordering-system.git
  cd crispy-broccoli-restaurant-ordering-system
```

Copy environment variables
```bash
cp .env.example .env
```

Start the database

```bash
docker compose up -d
```
---

## API Reference

http://localhost:8181/swagger-ui.html

### [Menu API](http://localhost:8181/api/menus)
- `GET /api/menus`
- `GET /api/menus/{id}`
- `POST /api/menus (admin)`
- `PUT /api/menus/{id} (admin)`
- `DELETE /api/menus/{id} (admin)`
### [Order API](http://localhost:8181/api/orders)
- `POST /api/orders`
- `GET /api/orders/{id}`
- `GET /api/orders/user/{userId}`
- `PUT /api/orders/{id}/status (staff)`
### [User API](http://localhost:8181/api/users)
- `POST /api/users/register`
- `POST /api/users/login`
- `GET /api/users/profile`
- `PUT /api/users/profile`
### [Payment API](http://localhost:8181/api/payments)
- `POST /api/payments`
- `GET /api/payments/{orderId}`

Services utilize [Spring-Doc]() for html documentation

---
## Database Schema

### Users
- id (PK)
- username
- email
- password_hash
- role (customer/staff/admin)

### Menus
- id (PK)
- name
- description

### MenuItems
- id (PK)
- menu_id (FK)
- name
- description
- price
- category
- image_url

### Orders
- id (PK)
- user_id (FK)
- status
- total_amount
- created_at
- updated_at

### OrderItems
- id (PK)
- order_id (FK)
- menu_item_id (FK)
- quantity
- subtotal

### Payments
- id (PK)
- order_id (FK)
- amount
- status
- payment_method
- transaction_id

---
## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`POSTGRES_USER`

`POSTGRES_PASSWORD`

`POSTGRES_DB`

---

## Implementation Steps
- [x] Set up Spring Boot project with necessary dependencies
- [x] Implement database models and repositories
- [x] Create RESTful API endpoints
- [x] Implement business logic in service layers
- [x] Set up Spring Security for authentication and authorization
- [] Integrate Stripe API for payment processing
- [] Implement WebSocket for real-time order updates
- [] Develop React frontend components and pages
- [] Integrate frontend with backend API
- [] Implement admin dashboard for restaurant management
- [] Add error handling and logging
- [] Perform thorough testing (unit, integration, and end-to-end)
- [] Deploy the application

---
## Todo
- [] Implement caching for frequently accessed data (e.g., menus)
- [] Use message queues for asynchronous processing of orders
- [] Implement rate limiting to prevent API abuse
- [] Add analytics to track popular menu items and peak ordering times
- [] Implement a review and rating system for menu items
- [] Add support for multiple languages and currencies
- [] Implement a loyalty program for frequent customers

---

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://dcatuns.vercel.app/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/devin-catuns/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/)

---
## Buy me a coffee

Whether you use this project, have learned something from it, or just like it, please consider supporting it by buying me a coffee, so I can dedicate more time on open-source projects like this :)

<a href="https://www.buymeacoffee.com/devincatunj" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>


---

## Acknowledgements


---

## Feedback

If you have any feedback, please reach out to us at devincatuns1@gmail.com

