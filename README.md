# Listings demo application

This is a demo application showcasing the use of [Java testcontainer](https://testcontainers.com/guides/getting-started-with-testcontainers-for-java/) in a web API project. 

This project is built with Sprint Boot using Java version 20.

This project is set up using maven profiles where certain profile combinations showcase use case with or without testcontainers. See the use cases with the needed profiles in the [Use cases and profiles section](#use-cases-and-profiles).

## Setup
A usable docker environment is needed to run this project.

In the project root there is a docker-compose file. To be able to run all scenarios of this project please start this docker-compose using the following command

```docker-compose up -d```

This will start a PostgreSQL DB container and a Redis container

## Project logic
In this API we have 2 entities that we provide a CRUD API for
 - Listings - items saved to the DB representing sale listings 
 - Drafts   - items are drafts of listings and saved only to a Redis instance for temporary storage

## Starting the project

### IntelliJ IDEA
To start the application in IntelliJ IDEA, in the Project sidebar navigate to com/avronnet/listings/ListingsApplication.java, right-click on the file and select run.

This will start the application with the currently selected profile's setup.
Profiles in this project are set up to showcase a web API project with and without using testcontainers.

## Testing
Test classes are structured in a way that each package shows a particular use case and project setup.

In the `api` test package is recommended to run only the corresponding test package for the scenario you are using (have activated profiles)

The `service` test package showcases service unit test using a mocking library to mock repositories.

## Use cases and profiles

### In-memory database
Profiles active: `h2`.
This is the default profile.

Corresponding `api` test package: `h2`.

When active the application will use an in-memory H2 database to save Listings items.
Each time we start the application we get an empty in-memory H2 database.

Test behavior is the same.

Entity behavior
 - Listings - saved to the H2 database
 - Drafts - saved to the Redis instance started by our docker-compose file

### PostgreSQL database
Profiles active: `pg`.

Corresponding `api` test package: `pg`.

The application uses the PostgreSQL database and the Redis instance started by the local docker-compose file.

Test behavior is the same.

Entity behavior
- Listings - saved to the PostgreSQL database
- Drafts - saved to the Redis instance started by our docker-compose file

### Testcontainers
Profiles active: `pg`.

Corresponding `api` test package: `testcontainers`.

The application behavior is the same as in [PostgreSQL database](#postgresql-database) scenario, it uses the PostgreSQL database and the Redis instance started by the local docker-compose file.

Test behavior is changed to use testcontainers in the ways described in the [Java testcontainers documentation](https://java.testcontainers.org/).
This means that testcontainers creates and runs docker container for the PostgreSQL and Redis each time we run our test and stops and removes them after the tests finish which gives us a clean start on each test run.

Entity behavior
- Listings - saved to a PostgreSQL database container spun up by testcontainers
- Drafts - saved to the Redis instance in a container spun up by testcontainers

### infobip-testcontainers tests only
Profiles active: `pg`, `use-infobip-testcontainers`.

Corresponding `api` test package: `infobip.testcontainers`.

The application behavior is the same as in [PostgreSQL database](#postgresql-database) scenario, it uses the PostgreSQL database and the Redis instance started by the local docker-compose file.

Test behavior is changed to use testcontainers but in a simplified way by using the [infobip-testcontainers-spring-boot-starter library](https://github.com/infobip/infobip-testcontainers-spring-boot-starter).
This means that infobip-testcontainers creates and runs docker container for the PostgreSQL and Redis each time we run our test and stops and removes them after the tests finish which gives us a clean start on each test run.

The use of infobip-testcontainers enables us to use testcontainer just by adding a couple dependencies and applicaton configuration instead of adding testcontainers in code.
This also gives us the option to easily call an initialization sql script for our postgre DB (before our code is given a connection to the DB) eg. to set up the database schema, by using the `testcontainers.postgresql.init-script` configuration property.

Entity behavior
- Listings - saved to a PostgreSQL database container spun up by testcontainers
- Drafts - saved to the Redis instance in a container spun up by testcontainers

### Using testcontainers for local development
Profiles active: `pg`, `use-infobip-testcontainers`, `development`.

Corresponding `api` test package: `infobip.testcontainers`.

On application start, just by adding some configuration changes, we are now spinning up docker containers for our PostgreSQL database and Redis and using them for our locally running application.

Test behavior is the same as in the [infobip-testcontainers tests only](#infobip-testcontainers-tests-only) scenario.

The use of infobip-testcontainers enables us to use testcontainer just by adding a couple dependencies and applicaton configuration instead of adding testcontainers in code.

Entity behavior
- Listings - saved to a PostgreSQL database container spun up by testcontainers
- Drafts - saved to the Redis instance in a container spun up by testcontainers
