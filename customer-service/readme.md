# Customer-Service

The implementation of this application demonstrates retrieving nearby customers given their latitude and longitude.

## What's in there?

1. `GPSPoint` type demonstrates calculating distance between two GPS points using Spherical Law of Cosines (formula)
2. `CustomerRepository` is built with simple interface that can be replaced with Spring JPA interfaces in case application data source changes to some DBMS.
3. `CustomerService` holds the service code that wires up and uses components like `GPSPoint`, `CustomerRepository` and `Mapper` 
4. `ExceptionHandlerController` sends global error responses to client. This makes it easy to handle error at one place. 
5. To keep it simple `Exceptions` are handled and thrown implicitly, . But in real world, one should create custom exception types and throw them up explicitly. 
6. `Appconfig` contains application components that are injected through out the execution life cycle.
7. `SecurityConfig` contains the components to configure basic security just to demonstrate the concept of API security.
8. Basic test cases are covered in Unit and Integration tests.
9. `TestConfig` is used in unit test context.
10. Data in test resources is used in all tests.
11. `Logging` is just to cover basic logging concept.
12. The application has two basic profiles one for dev and other for prod.

## Running the application
using maven
`mvn spring-boot:run`

to deploy in production build project using maven `mvn clean package` and deploy using `java -jar -Dspring.profiles.active=prod [customer-service jar file name]`

Once the application started browse http://localhost:8000/api/customers (for production point to port no 8001) enter credentials username `admin` and password `admin`
