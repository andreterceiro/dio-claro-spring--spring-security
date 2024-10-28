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

![](images/initial-page.png)


