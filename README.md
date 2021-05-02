# atsarat-briut-microservices

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
