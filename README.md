# atsarat-briut-microservices
Atsarat bruit filling at a atsarat-briut for “Tsofim” by link https://briut.robins.app/main

detail-description ->
https://github.com/natalyaKh/atsarat-briut-microservices/projects

<img width="501" alt="Снимок экрана 2021-05-02 в 13 38 50" src="https://user-images.githubusercontent.com/54761439/116810489-b48dfb00-ab4c-11eb-8fe1-9e439f737fdf.png">

This is a project with Microservices structure, that demonstrated Microservices Architecture Pattern. Project use
•	SpringBoot
•	Spring Cloud
•	Java 11
•	Docker
•	RanbbitMQ


# FunctionServices:
# User-service

    implements the logic and validation for user registration. All information that concerns users (parent's name, surname, tz, email) is stored and processed in this service.
    In addition, he is also responsible for registering the user.

# Children-service 
    
    implements the logic that concerns the child (his school, class, tz, etc. information necessary to fill in the atsarat shave)

    TZ in both services is encrypted using Base64

# Scgeduler-service
![image](https://user-images.githubusercontent.com/54761439/116811047-ddfc5600-ab4f-11eb-94aa-c98000362363.png)

    a service that, according to a predetermined schedule (using Corn), checks whether the tofes needs to be filled (daily at 7:00 am).
    
    In addition, this service is responsible for removing information about users with an unconfirmed email address from the database (it is checked once a week). If the service detects that there is a need to fill in the tofes, it sends a message to the corresponding service, which contains the uuid of the child for whom it is necessary to compose a shave atsarat.
    (implemented with RabbitMQ)


# Tsofim-service

    Сервис который отвечает  за заполнение документа. Получив messageот scheduler- service, the service "goes" sends a request to user-service to get data about the parent, and to children-service to get data about the child.
    Messaging is implemented using hystrix to control failures and delays. If an error occurs during the call, the fallback method is used, which returns the default error message.
    
    After the service has collected all the necessary data from other services, it, using Selenium, fills in the tofes by entering the necessary data, and when everything is ready, it takes a screenshot of the screen.
    A screen shot is sent to an e-mail along with the data necessary to send a confirmation letter to the child's parents.
    (implemented with RabbitMQ)

![image](https://user-images.githubusercontent.com/54761439/116811051-e6ed2780-ab4f-11eb-92f6-a33a5888fc20.png)

# Email-service

    a service that sends messages to users. The service "listens" to messages from the user-service Tsofim-service gymnast-service school-service.
    

Communication between microservices occurs both synchronously and asynchronously.


Each individual microservice is a Rest Back Server Back-service includes 2 levels:

    1.	Controller. This is Spring Boot Rest Controller. Used for client - server communication. The controller consolidates all request processing by routing them through a single developer object.
    2.	Service, which contains all the business logic of data processing. At the same level, there are the classes required for communication with the database. These classes are configured to implement the DAO pattern. These are the classes required to provide a CRUD interface for a single object. DAO - Spring Boot JPA Hibernate uses SQL database. The database was chosen because of its structure, which contains several separate independent entities.

# Note:

        -	Each microservice has its oven database
        -	In project use MySql 1.5
        -	Service-toservice communication – RestApi + RabbitMq messages

## Infrastructure-services:
There is a bunch of common patterns in distributed system.

# AUTHENTICATION
![image](https://user-images.githubusercontent.com/54761439/116811065-f4a2ad00-ab4f-11eb-8273-5fad201d9434.png)


    Using JWT security. Registration of user and login are in User-service. But authentication and authorization implement in gateway-service.

# API-GATEWAY

    It is a single entry point into the system, used to handle requests by routing them to the appropriate backend service or by invoking multiple backend services and aggregating the results. Also, it can be used for authentication, insights, stress and canary testing, service migration, static response handling, active traffic management.
    In project uses tool of Netflix – Zuul.
    requests from api-gateway are not checked by authentication. All services in structure getting requests from port of zuul-service as permitAll()

# SERVICE-DISCOVERY

    Service discovery allows automatic detection of network locations for service instances, which could have dynamically assigned addresses because of auto-scaling, failures and upgrades.
    The key part of Service discovery is Registry. using Netflix Eureka in this project. Eureka is a good example of the client-side discovery pattern, when client is responsible for determining locations of available service instances (using Registry server) and load balancing requests across them.

# RIBBON

    Ribbon is a client side load balancer which gives you a lot of control over the behaviour of HTTP and TCP clients. Compared to a traditional load balancer, there is no need in additional hop for every over-the-wire invocation - you can contact desired service directly.
    Out of the box, it natively integrates with Spring Cloud and Service Discovery. Eureka Client provides a dynamic list of available servers so Ribbon could balance between them.
# HYSTRIX

    Hystrix is the implementation of Circuit Breaker pattern, which gives a control over latency and failure from dependencies accessed over the network. The main idea is to stop cascading failures in a distributed environment with a large number of microservices. That helps to fail fast and recover as soon as possible - important aspects of fault-tolerant systems that self-heal.
    Besides circuit breaker control, with Hystrix you can add a fallback method that will be called to obtain a default value in case the main command fails.
    Moreover, Hystrix generates metrics on execution outcomes and latency for each command, that we can use to monitor system behavior.
# MONITORING

    In this project monitoring realized with admin-service, which collects all metrics from services.
# LOG ANALYTICS

    Centralized logging can be very useful when attempting to identify problems in a distributed environment. Elasticsearch, Logstash and Filebeat  stack lets you search and analyze your logs, utilization and network activity data with ease.
# DISTRIBUTED TRACING

    Distributed tracing implemented with Spring Cloud Sleuth and Zipkin.



add rabbit MQ -> 

        - docker run -d --hostname rabbitmq  --name rabbitmq -p 5672:5672 -p 15672:15672 -p 15674:15674 -p 25672:25672 -p 61613:61613 -v rabbitmq_data:/var/lib/rabbitmq -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3.6.14-management
        - http://localhost:15672/

Login -> 

    -localhost:8080/users/login

Swagger -> 

    - http://localhost:8011/swagger-ui.html#!/

For creating image -> 

    - maven clear
    - maven package
    - maven -> plugins-> docker plugin -> build

Example for creating image and pushing to docker hub

    - mvn clean
    - mvn package
    - docker build
    - CLI -> 
        - docker tag atsarat/eureka:latest smilyk/eureka:latest
        - docker push smilyk/eureka:latest

сборка проекта ->  перейти в главную папку проекта 

    - .../atsarat-briut-microservices %
    - sh start.sh

javadoc

    - http://localhost:63342/atsarat-briut-microservices/children-service-javadoc/index.html
    - http://localhost:63342/atsarat-briut-microservices/email-service-javadoc/index.html
    - http://localhost:63342/atsarat-briut-microservices/tsofim-service-javadoc/index.html
    - http://localhost:63342/atsarat-briut-microservices/scheduler-service-javadoc/index.html
    - http://localhost:63342/atsarat-briut-microservices/user-service-javadoc/index.html
