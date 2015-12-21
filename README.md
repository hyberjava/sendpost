###Post sender / response checker

Отправляет пост-запросы, и выводит ответы, сообщая какой из тестов вы не проходите по причине несоответствия response code или response request.


======================


```
> Отправляет POST запрос на http://127.0.0.1:8080/signup c параметрами login=newUser&password=newPassword
> Ждет code response 200, если ответ 200 выводит TEST PASSED(тест пройден), если ответ не 200 выводит TEST FAILED(тест провален)
> Отправляет POST запрос на http://127.0.0.1:8080/signin c параметрами login=newUser&password=newPassword
> Ждет code response 200, если ответ 200 
  >> Ждет request Authorized, если реквест Authorized выводит TEST PASSED(тест пройден), если ответ не Authorized выводит TEST FAILED(тест провален)
> если ответ на code response не 200 выводит TEST FAILED(тест провален)
```


======================


####DOWNLOAD
[скачать](https://github.com/hyberjava/sendpost/blob/master/sendpost.jar)

####HOWTO
запускать при уже запущенном в IDE или еще каким способом сервере:

```
java -jar sendpost.jar
```
