# atsarat-briut-microservices

add rabbit MQ -> 

        - docker run -d --hostname rabbitmq  --name rabbitmq -p 5672:5672 -p 15672:15672 -p 15674:15674 -p 25672:25672 -p 61613:61613 -v rabbitmq_data:/var/lib/rabbitmq -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3.6.14-management
        - http://localhost:15672/

Login -> 

    -localhost:8080/users/login

Logout -> 

    -localhost:8080/users/logout
