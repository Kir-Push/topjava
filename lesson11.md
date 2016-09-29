# Онлайн проекта <a href="https://github.com/JavaWebinar/topjava07">Topjava</a>
## Все материалы проекта в <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFflp6ZHBLSFI2OGVEZ2NQU0pzZkx4SnFmOWlzX0lzcDFjSi1SRk5OdzBYYkU">Google Drive</a> (перенес в корень)

### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 1. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFSG5jeEVCTzZPbmc">Refactoring & Fix</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFT0lOZG1IZ1NydEU">0-refactoring-fix.patch</a>**
- Рефакторинг REST контроллеров и обработка ошибок по `@ControllerAdvice(annotations = RestController.class)`
- <a href="https://www.javacodegeeks.com/2013/11/controlleradvice-improvements-in-spring-4.html">@ControllerAdvice improvements in Spring 4</a>
- Правка UTF-8 в редактировании профиля и регистрации (порядок `encodingFilter` и `springSecurityFilterChain` фильтров)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Разбор домашнего задания HW10

### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 2. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFaDN2V1g0bG9EaFE">HW10</a>
>  Обработка валидации формы для Ajax контроллеров сделана через `org.springframework.validation.BindException`

>  Добавилась валидация в REST контроллерах  

>  Добавилась локализация `duplicate_email`

>  Рефакторинг `ErrorInfo` : `String detail` -> `String[] details`

-  **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFQjlicXI2TVBqNnM">1-HW10-validation.patch</a>**
-  **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFLS10azVSSEFuN1E">2-HW10-email-duplicate.patch</a>**

###  ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 3. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFYms4YUxEMHdxZHM">HW10 Optional: change locale</a>
-  **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFSzQ4TnJIWnh2OHM">3-HW10-change-locale.patch</a>**

>  еще вариант смены локали в lang.jsp вместо javascript: `href=${requestScope['javax.servlet.forward.request_uri']}?lang=..`

- <a href="http://forum.spring.io/forum/spring-projects/web/1077-differences-between-spring-message-and-fmt-message">spring-message vs fmt-message</a>
- <a href="http://pro-cod.ru/navbar-uroki-bootstrap-3.html">Bootstrap Navbar</a>

## Заключительное занятие

### 4. Рефакторинг
> Добавилась авторизация в `RootControllerTest`

- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFbXh1MlF2bWlxbnc">4-authorize-controller-test.patch</a>**
  
> Рефакторинг Matchers: 
  - в `ModelMatcher` добавил универсальный `EntityWrapper`, который сравнивает объекты по переданному в конструктор `EntityComparator`. Таким образом класс `UserTestData.TestUser` стал не нужен.
  - `UserUtil.prepareToSave` перенес в сервис `UserServiceImpl`.  

- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFajVQRjJkZ2FCQmc">5-refactor-matchers.patch</a>**

### 5. Фильтруем JSON через @JsonView
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFamNaX1ZUSzBPNVk">6-JsonView.patch</a>**
- <a href="https://habrahabr.ru/post/307392/">@JsonView: фильтруем JSON</a>

###  ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png)  <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFZkpVM19QWFBOQ2c">6. Деплой приложения в Heroku.</a>
-  **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFajU5XzBWTUJNZFU">7-heroku.patch</a>**

> В `DataJpaUserRepositoryImpl` добавилась защита от удаления-модификации Admin/User

-  <a href="http://habrahabr.ru/post/265591/">Деплой Java Spring приложения в PaaS-платформу Heroku</a>
```
Config Vars
  ERROR_PAGE_URL=...
  TOPJAVA_ROOT=/app

Datasources advanced
    ssl=true
    sslfactory=org.postgresql.ssl.NonValidatingFactory
```    

-  Ресурсы:
   -  <a href="https://www.heroku.com/">PaaS-платформа Heroku</a></h3>
   -  Конфигурирование приложения для запуска через <a href="https://devcenter.heroku.com/articles/java-webapp-runner">Tomcat-based Java Web</a>
   -  Конфигурирование <a href="https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#using-the-database_url-in-spring-with-xml-configuration">DataSource profile для Heroku</a>
   -  <a href="http://www.jetbrains.com/idea/webhelp/run-debug-configuration-heroku-deployment.html">Интерграция с IDEA</a>
   -  <a href="http://www.paasify.it/filter">Find your Platform as a Service</a>
   -  <a href="https://devcenter.heroku.com/articles/getting-started-with-java#set-up">Getting Started with Java on Heroku</a>
   -  <a href="https://devcenter.heroku.com/articles/keys">Managing Your SSH Keys</a>
   -  <a href="https://devcenter.heroku.com/articles/getting-started-with-spring-mvc-hibernate#deploy-your-application-to-heroku">Deploy your application to Heroku</a>
   -  <a href="http://www.ibm.com/developerworks/ru/library/j-javadev2-21/">Развертывание приложений Java с помощью PaaS от Heroku</a>
   -  <a href="http://www.infoq.com/articles/paas_comparison">A Java Developer’s Guide to PaaS</a>
   -  <a href="https://dzone.com/articles/simple-paas-comparison-guide">A Simple PaaS Comparison Guide (With the Java Dev in Mind)</a>
   -  <a href="http://www.ibm.com/developerworks/library/j-paasshootout/">Java PaaS shootout</a>

## Домашнее задание по проекту:
- Задеплоить свое приложение в Heroku. Понять, почему в окончательном варианте для деплоя часть тестов не проходит. 
- Сделать / обновить резюме (отдать на ревью мне или в группу)
- <a href="https://github.com/JavaOPs/topjava/blob/master/cv.md">Составление резюме, подготовка к интервью, поиск работы</a>
- <a href="http://goo.gl/forms/Oy5A7HNkWt">Заполнить форму соискателя "Разработчик Java"</a>.
- Выполнить <a href="https://github.com/JavaWebinar/topjava07/blob/doc/doc/graduation.md#graduation-project">Graduation project</a>
  - Для участников с проверкой выпускной проект желательно сдать на ревью до 01.09.
  - В выпусконом проекте (тестовом задании на работу) НЕ надо делать профили базы и работы с базой. В отличии от нашего учебного оставляйте только необходимый для работы код. 
   
## Возможные доработки приложения:
-  Для редактирования паролей сделать отдельный интерфейс с запросом старого пароля и кнопку сброса пароля для администратора.
-  Добавление и удаление ролей для пользователей в админке.
-  Перевести UI на <a href="http://angular.ru/">angular.js</a> / <a href="https://vaadin.com/elements">Vaadin elements</a> /GWT /GXT /Vaadin / ZK/ [Ваш любимый фреймворк]..
-  Перевести проект на <a href="http://projects.spring.io/spring-boot/">Spring Boot</a>
-  Перевести шаблоны с JSP на <a href="http://www.thymeleaf.org/doc/articles/petclinic.html">Thymeleaf</a>
-  Сделать авторизацию в приложение по OAuth 2.0 (<a href="http://projects.spring.io/spring-security-oauth/">Spring Security OAuth</a>,
<a href="https://vk.com/dev/auth_mobile">VK auth</a>, <a href="https://developer.github.com/v3/oauth/">github oauth</a>, ...)
-  Сделать подтверждение регистрации пользователя по email c возможностью восстановления пароля.
-  Сделать отображение еды постранично, с поиском и сортировкой на стороне сервера.
-  Перевод проекта на https
-  Сделать desktop/mobile приложение, работающее по REST с нашим приложением.
-  Локализация сообщений об ошибках, показ ошибок в форме редактирования таблицы в стиле JSP.
-  <a href="http://www.mkyong.com/spring-security/spring-security-limit-login-attempts-example">Limit login attempts example</a>
-  Любая на ваше усмотрение.

**Делитесь своими доработками!**

### Ресурсы по Проекту
-  <a href="http://pro-cod.ru/uroki-bootstrap-3-0">Уроки Bootstrap 3</a>
-  <a herf="http://www.tutorialspoint.com/spring/index.htm">Spring at tutorialspoint</a>
-  <a href="http://www.codejava.net/frameworks/spring">Articles in Spring</a>
-  <a href="http://www.baeldung.com/learn-spring">Learn Spring on Baeldung</a>
-  <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/index.html">Spring Framework
            Reference Documentation</a>
-  <a href="http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/">Hibernate Reference Documentation</a>
-  <a href="http://java-course.ru/student/book2/">Java Course (книга 2)</a>
-  <a href="http://design-pattern.ru/">Справочник «Паттерны проектирования»</a>
-  <a href="http://martinfowler.com/eaaCatalog/">Catalog of Patterns of Enterprise Application Architecture</a>

# <a href="http://goo.gl/forms/PtFcPqEV5n">Короткий опрос для всех участников проекта</a>

-  В письме я вышлю ссылку для бесплатного повторного участия (первый урок 08.09) и ссылку на добавление в группу Slak "Java Online Projects", кто еще не добавился.
-  Все материалы (в том числе и обновления в следующих проектах) останутся вам доступны в Google Drive.

## Обновления
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFVUs0LTh6NU9tZnM">8_fix.patch</a>**
  - Небольшой фикс:
    - Починка отображения даты регистрации: `dateObject.toISOString()` не работает в Сафари
    - Удаление лишнего `jul-to-slf4j`. Согласно <a href="https://www.youtube.com/watch?v=qzqAUUgB3v8">видео Владимир Красильщика про логирование</a>  он замедляет работу  
    - Удалил лишние строки из `datatablesUtil.js`
  
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFQl9LSjBsMjZGdU0">9_heroku_test_repair.patch</a>**
  - Починил тесты: запрет на модификацию admin/user происходит только если активирован Spring профиль `heroku`.
    - Перенес проверку в `AbstractUserController`
    - Активные профили достаются в заинжектенном `Environment environment`
    - Добавил тесты на запрет модификации
       
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFTXNzcUUzVUpRQ3c">10_js_i18n.patch</a>**
  - Добавил простую <a href="http://stackoverflow.com/questions/6218970/resolving-springmessages-in-javascript-for-i18n-internationalization#6242840">локализацию в JavaScript</a>. 
    - В `i18n.jsp` на стороне сервера формируется JavaScript массив с локализированными значениями, который затем используется для интернационализации в браузере.  
