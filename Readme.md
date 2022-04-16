# ReadingIsGood Online Books Retail

Online books retail project developed using SpringBoot and MongoDB.

All services except the /login service work with bearer token. Therefore, first of all, the /login service should be called with 
```
{"username": "admin", "password": "admin"}
```
Bearer token information should be added in the header field of other services as follows.

```
Headers:
		Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJz...
```
Bearer token is valid for 15 days.

Detailed API specification for other services is available in the swagger interface.


## Technology

-   **Spring Boot** 
-   **Mongo DB**
-   **JPA** 
-   **Lombok**
-   **JJWT**
-   **Swagger**
-   **Docker** 
-   **Junit** 
-   **Spring Security**
-   **Spring Data**

## Docker

In this project, application and mongodb are dockerized.

**Dockerfile**

To create an image of the application use -
```
docker build -t readingisgood .
```
**Docker-Compose**

To run the new image and mongo, use -
```
docker-compose up
```
## Swagger UI

Swagger can be used for api specification can be accessed at the following URL -

```
http://localhost:8080/swagger-ui/index.html#
```
## Unit Test

There are multiple unit test cases written to cover the different components of the application. 

These:
```
BookControllerTest.java
CustomerControllerTest.java
OrderControllerTest.java
```

Batuhan GÖKTAŞ
[LinkedIn](https://www.linkedin.com/in/batuhan-g%C3%B6kta%C5%9F-29035aa8/)
