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
* [Spring Boot: 3.2.5](https://docs.spring.io/spring-boot/docs/current/reference/html/)

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

Start the server

```bash
docker compose up -d
```
---

## API Reference

API Gateway is set to port `8080` by default
`http://localhost:8080/`

### Menu API
- `GET /api/menus`
- `GET /api/menus/{id}`
- `POST /api/menus (admin)`
- `PUT /api/menus/{id} (admin)`
- `DELETE /api/menus/{id} (admin)`
### Order API
- `POST /api/orders`
- `GET /api/orders/{id}`
- `GET /api/orders/user/{userId}`
- `PUT /api/orders/{id}/status (staff)`
### User API
- `POST /api/users/register`
- `POST /api/users/login`
- `GET /api/users/profile`
- `PUT /api/users/profile`
### Payment API
- `POST /api/payments`
- `GET /api/payments/{orderId}`

Services utilize [Spring-Doc]() for html documentation


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`POSTGRES_USER`

`POSTGRES_PASSWORD`

`POSTGRES_DB`

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

## Documentation

[Documentation](https://linktodocumentation)

---

## Feedback

If you have any feedback, please reach out to us at devincatuns1@gmail.com

