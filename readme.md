# Microservices 

## About Project

This is my solo project, designed to be a platform that familiarizes me with industry-standard applications and their integration into a project. I've taken it a step further by building it as a microservices architecture.

The project is centered around the concept of an e-commerce application. It features a Warehouse, Checkout, and Messaging services. The Frontend is currently under development. However, due to time constraints and commitments to another project, progress has slowed.

## Tech stack

[![Spring Boot][springBoot-badge]][springBoot-url]
[![Spring Security][springSecurity-badge]][springSecurity-url] 
[![Hibernate][hibernate-badge]][hibernate-url] 
[![Apache Maven][apacheMaven-badge]][apacheMaven-url] 
[![Nginx][nginx-badge]][nginx-url] 
[![Keycloak][keycloak-badge]][keycloak-url] 
[![Docker][docker-badge]][docker-url]
[![PostgreSQL][postgresql-badge]][postgresql-url]
[![Redis][redis-badge]][redis-url]
[![Rabbitmq][rabbitmq-badge]][rabbitmq-url]
[![Vite][vite-badge]][vite-url]
[![React][react-badge]][react-url]
[![mui][mui-badge]][mui-url]

## Architecture

- **Nginx** - Edge Proxy
  - **Warehouse Service** 
    - Catalog
    - Inventory
    - Delivery
  - **Checkout Service**
    - User details
    - Order history
    - Dummy payment
  - **Message Service**
    - Email Sending
  - **Keycloak**
    - OAuth 2.0
  - **Redis**
    - Cache for reserved items
  - **Rabbit MQ**
  - **PostgreSQL**
  - **Frontend**
    - Nginx web server
    - Authentication authorization via Keykloak

## Key service details

### Warehouse Service

The Warehouse Service is responsible for managing the inventory and catalog items. It plays a crucial role in the delivery process by:

- **Connection with Checkout Service:** Maintains a connection with the Checkout Service via WebClient. This allows the Warehouse to receive information about reserved items and to send confirmations once orders are delivered. These confirmations trigger the deletion of the reserved items in the system.

- **RabbitMQ Integration:** Integrates a RabbitMQ consumer that handles incoming orders. This ensures efficient processing and communication within the service architecture

The service also utilizes Spring Security OAuth2 for secure authentication and authorization, structured as follows:
- **OAuth2 Client**
  - **Authorize requests:** Manages secure authorization between the client and various services.

- **OAuth2 Resource Server**
  - **JWT Token Handling:** Allows the WebClient to obtain JWT tokens, ensuring safe inner communication between services.

### Checkout Service 

The Checkout Service handles various functionalities related to user information, order management, and payment processing. Its key responsibilities include:

- **User Information Management:** Stores and manages user-related data.
- **Order Processing:** Facilitates the process of placing and managing orders.
- **Payment Handling:** Manages payment transactions via WebClient *"Dummy Payment"*.
- **Integration with Warehouse Service:** Maintains a connection with the Warehouse Service via WebClient to retrieve information about item prices.
- **RabbitMQ Integration:** Utilizes a RabbitMQ producer to:
  - Send emails for order notifications and order state changes through the Message Service.
  - Forward paid orders to the Warehouse Service for further processing.
- **Caching:** Utilizing Redis fast memory key-value storage to keep track of the ordered items between placing order payment and after the Warehouse process the delivery; it removes the item from the cache.

The service also utilizes Spring Security OAuth2 for secure authentication and authorization, structured as follows:
- **OAuth2 Client**
  - **Authorize requests:** Manages secure authorization between the client and various services.

- **OAuth2 Resource Server**
  - **JWT Token Handling:** Allows the WebClient to obtain JWT tokens, ensuring safe inner communication between services.

### Message Service

The Message Service primarily focuses on facilitating email communication within the system. Its functionality is currently basic but can be improved upon. Key aspects of the Message Service include:

- **Email Sending:** Handles the sending of emails to customers whit the help of Google smtp.
- **RabbitMQ Message Consumption:** Consumes messages from RabbitMQ, likely pertaining to order notifications and updates.
- **Basic Formatting:** Performs basic formatting of messages before sending them to customers.

While the Message Service currently serves its purpose, there is room for improvement to enhance its capabilities and efficiency.

### Frontend 

The current state of the frontend is capable of managing the login process with iframes via Keycloak and partially handles the order process. However, the styling is basic or missing. With the assistance of Material UI, enhancing the styling should be straightforward.

During the development of the frontend, certain challenges were encountered:
- To enable live reload, a Vite development container needed to be added. This task was not trivial on Windows machines due to differences in file systems.
- Additionally, the WebSocket port of the Vite dev server had to be transferred from the Docker environment to the local network to enable hot reload.

In the production build, Nginx web server will be utilized to serve the static assets.

## How to Get Started
How to Get Started

The project utilizes Docker containerization technology, requiring minimal configuration for all functionality. Follow these steps to get started:

1. **Clone the Resources:**
   After cloning the project's resources into your desired directory, navigate to the project root. Here, you will find a `.env.sample` file. Provide your Google SMTP username and password in this file. Alternatively, if you prefer to use a different SMTP provider, you can configure it in the `docker-compose.yml` file.

1. **Configuration:**
   Configure the `.env` file according to your requirements.

1. **Build and Run:**
   Open a console or your favorite IDE with Docker support, then start the build using the `docker-compose.yml` file. Here's a command to help you in the console: `docker compose up`


1. **Access the Application:**
Once the containers are up and running, you can access the application.

1. **Prepared Accounts:**
The application comes with some built-in data, including prepared accounts for testing purposes:

  | Email | Password |
  | --- | --- |
  | admin@admin.com | admin |
  | user@user.com | user |

6. **Test with Postman:**
For testing, you can use Postman along with the additional JSON files provided in the `./postman` folder to interact with the application.

## Future Plans

The project has ample room for improvement, and the following roadmap outlines the planned enhancements:

- [ ] **Frontend:**
  - Finish the main workflow to enhance user experience.

- [ ] **Testing:**
  - Write additional unit tests to ensure robustness and reliability.

- [ ] **Admin/Employee Functionality:**
  - Implement frontend and backend functionality for admin/employee roles to manage the system effectively.

- [ ] **Warehouse Service:**
  - Transition automatic package processing to an employee task-based system for improved control and oversight.

- [ ] **Message Service:**
  - Develop CRUD functionality for message templates to facilitate better communication management.

These planned enhancements will contribute to the project's overall functionality, usability, and efficiency.

<!-- Badge links -->
[springBoot-badge]: https://img.shields.io/badge/spring_boot-black?style=for-the-badge&logo=springboot&logoColor=white
[springSecurity-badge]: https://img.shields.io/badge/spring_security-black?style=for-the-badge&logo=springsecurity&logoColor=white
[hibernate-badge]: https://img.shields.io/badge/hibernate-black?style=for-the-badge&logo=hibernate&logoColor=white
[apacheMaven-badge]: https://img.shields.io/badge/apache_maven-black?style=for-the-badge&logo=apachemaven&logoColor=white
[nginx-badge]: https://img.shields.io/badge/nginx-black?style=for-the-badge&logo=nginx&logoColor=white
[keycloak-badge]: https://img.shields.io/badge/keycloak-black?style=for-the-badge&logo=keycloak&logoColor=white
[docker-badge]: https://img.shields.io/badge/docker-black?style=for-the-badge&logo=docker&logoColor=white
[postgresql-badge]: https://img.shields.io/badge/postgresql-black?style=for-the-badge&logo=postgresql&logoColor=white
[redis-badge]: https://img.shields.io/badge/redis-black?style=for-the-badge&logo=redis&logoColor=white
[rabbitmq-badge]: https://img.shields.io/badge/rabbitmq-black?style=for-the-badge&logo=rabbitmq&logoColor=white
[react-badge]: https://img.shields.io/badge/react-black?style=for-the-badge&logo=react&logoColor=white
[vite-badge]: https://img.shields.io/badge/vite-black?style=for-the-badge&logo=vite&logoColor=white
[mui-badge]: https://img.shields.io/badge/Material_UI-black?style=for-the-badge&logo=mui&logoColor=white

<!-- Project url -->
[springBoot-url]: https://spring.io/projects/spring-boot
[springSecurity-url]: https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html
[hibernate-url]: https://docs.spring.io/spring-framework/reference/data-access/orm/hibernate.html
[apachemaven-url]: https://maven.apache.org/
[nginx-url]: https://www.nginx.com/
[keycloak-url]: https://www.keycloak.org/
[docker-url]: https://www.docker.com/
[postgresql-url]: https://www.postgresql.org/
[redis-url]: https://redis.io/
[rabbitmq-url]: https://www.rabbitmq.com/
[react-url]: https://react.dev/
[vite-url]: https://vitejs.dev/
[mui-url]: https://mui.com/

