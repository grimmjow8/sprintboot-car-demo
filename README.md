springboot-car-demo
===

Springboot demo app that implements a RESTful application for a car dealership.

Services
====
- Car information endpoints 
 - POST   - /v1/car      - add car data
 - GET    - /v1/car/{id} - retrieve car data
 - DELETE - /v1/car/{id} - delete car data

Setup
====
- Install docker
- Update gradle.properties 'org.gradle.java.home' attribute.

Usage
====
- To build
 $ ./gradlew buildApp
- To run (start server)
 $ ./gradlew runApp

- Example curl commands:
    - curl -X GET localhost:8080/v1/car
    - curl -X POST -H "Content-Type: application/json" -d '{"make":"make1","model":"model1","year":2006}' localhost:8080/v1/car
    - curl -X DELETE -H "Content-Type: application/json" localhost:8080/v1/car/4b66ff46-fd55-4c0e-b882-7e5d36802622
