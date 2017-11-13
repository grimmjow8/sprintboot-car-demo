springboot-car-demo
===

Springboot demo app that implements a RESTful application for a car dealership.

Services
====
- Car information endpoints 
    - GET    - /v1/car      - retrieve car data (all cars)
    - GET    - /v1/car/{id} - retrieve car data (single car)
    - POST   - /v1/car      - add car data
    - DELETE - /v1/car/{id} - delete car data

Setup
====
- Install docker
- Update gradle.properties 'org.gradle.java.home' attribute.

Usage
====
- To build
    - $ ./gradlew buildApp
- To run (start server)
    - $ ./gradlew runApp

- Example curl commands:
    - $ curl -X GET localhost:8080/v1/car
    - $ curl -X POST -H "Content-Type: application/json" -d '{"make":"make1","model":"model1","year":2006}' localhost:8080/v1/car
    - $ curl -X DELETE -H "Content-Type: application/json" localhost:8080/v1/car/4b66ff46-fd55-4c0e-b882-7e5d36802622

Assumptions/Design Decisions
====
Below is a list of assumptions and design decisions made during development:
- No update of car information, information is added & removed all at once.
- Limited scope by only supporting the following fields: make, model, year.
- Stored information in-memory using a ConcurrentHashMap.
- Integration testing added for the endpoints, while unit tests added for the service and error layer.
- Gradle/Docker build process used to make it easier for others to test/verify.
- Support localization of error strings.
- Support error handling for invalid IDs and duplicate data.
- No error handling provided for data sanitation.

Future
====
- [ ] Add Swagger
- [ ] Add Travis/CI pipeline
- [ ] Refactor build process
