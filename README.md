# FastmartBackend

**Fasmart** is a simple ecommerce application used for shopping variety of products. FastmartBackend is the backend code repository for it.

## Tech Stack
Java, Spring boot 2.6, Spring data MongoDB, Spring Security, JWT token authentication, Thymeleaf template engine, Java Mail Server 
Google Guava, Heroku

## Runbook
1. Update your secrets in `src/main/resources/application-example.properties`
2. Rename the file `src/main/resources/application-example.properties` to `src/main/resources/application.properties`
3. Run the SpringBootApplication using your IDE

## App Bootstrap
1. `SaveStarterProducts.java` bootstraps the app automatically with starter products present in `starter-products.json`

## Deployment
This application is hosted on Heroku platform. Secrets are managed using Heroku's config vars. Check `src/main/resources/application-heroku.properties`

## Future Work
1. Creating Admin user and developing Admin access specific APIs
2. Admin should be able to add/update products
3. Admin should be able to list and search users
4. Bootstrapping Admin and service account creation
5. Managing secrets.
