Решение проблемы Unable to push 'atsaratchildren' : denied: requested access to the resource is denied

1) Войдите в докер.

docker login -u sirimalla
2) Пометьте свой имидж строения

мое имя изображения здесь: mylocalimage, и по умолчанию оно имеет тег: последний,
и мое имя пользователя: sirimalla, зарегистрированное в облаке Docker , и я создал публичный репозиторий с именем: dockerhub

так что мой личный репозиторий становится теперь: sirimalla / dockerhub, и я хочу добавить свое изображение с тегом:myfirstimagepush

Я отметил как ниже:

docker tag mylocalimage:latest sirimalla/dockerhub:myfirstimagepush
3) Вставил изображение в мой личный докер репозиторий, как показано ниже

docker push sirimalla/dockerhub:myfirstimagepush
И это успешно подтолкнуло к моему личному докер репо.


решение проблемы по созданию очередей в rabbitMQ

    - links:
        -https://stackoverflow.com/questions/31662727/define-rabbitmq-policies-in-configuration-file
        -https://stackoverflow.com/questions/30747469/how-to-add-initial-users-when-starting-a-rabbitmq-docker-container
Так как rabbitMQ не разрешает создавать очереди не из localhost(нужны супер права администратора), можно решить вопрос 
тем, что создать свой кастомный docker контейнер rabbitMQ, который при старте будет создавать очереди со всеми 
необходимыми им зависимостями

Для этого:
    
    -1. в rabbitMQ создаем Dockerfile в котором расскажем приложению, где брать файлы конфигурации
    -2. создаем rabbitmq.config
    -3. создаем definition.json именно в этом файле мы можем прописать добавление пользователей, добавление очередей 
    и всех зависимостей для очередей в rabbitMQ

после того как файлы созданы, можно созадвать новый образ и пушить его в докер
    
    - mvn clean
    - mvn package
    - docker build
    - docker tag atsarat/rabbit  smilyk/rabbit:latest
    - docker push smilyk/rabbit:latest
теперь можно подключать кастомный rabbitMQ image в compose.yml файл и запускать его в работу. При старте контейнера rabbit
создать всех пользователей с указанными правами, и необходимые очереди.


