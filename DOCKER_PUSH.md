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

