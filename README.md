# General

## Initial links

Class [link](https://web.dio.me/course/adicionando-seguranca-a-uma-api-rest-com-spring-security/learning/99032de1-b0da-4986-9907-6028acc4202d?back=/track/coding-the-future-claro-java-spring-boot&tab=undefined&moduleId=undefined)

Github [repository link](https://github.com/digitalinnovationone/dio-springboot)


## Enabling

![enabling Spring security](images/enabling-spring-secutity.png)


## Project generated

This is how I generated the project in [[http://start.spring.io](http://start.spring.io)]:

![project generated](images/project-generated.png)

I generated the project of the file `project-generated-with-initializr.zip` and unpacked the files in the root directory.

I also has to create the file `application.properties` in the root directory with this content:

```
server.port=8081
```

After, I runned the commands:

```
mvn install
mvn spring-boot:run
```

In the root link I saw this page after Spring Boot was receiving requests:

![initial page](images/initial-page.png)

To use the default user (who is `"user"`), please locate the password in the console:

![default password](images/default-password.png)

To created a hardcoded (not recommended, only to simplify) user and password, please insert this configuration in the `application.properties` file:

```
spring.security.user.name=user
spring.security.user.password=password
spring.security.user.roles=USERS
```


## Simple authentication in memory

Teacher enabled a simple authentication in memory this way. This involves deprecated things nowdays, so I only made [this video](https://youtu.be/3Vg_elM7KXw) about this thing. 