# Message

To be clear. The content here is a content based on a free course. Noone can reproduce this material. If you wanna to know this material, my advice is to you access the **free** [course](https://web.dio.me/track/coding-the-future-claro-java-spring-boot). And if they end the course or start to ask money to study in the course? Well, this considerations are out of my control, but the DIO material is excelent and the course is amazing! If you acquire a DIO paln, you will have access to amazing courses.

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

After, I ran the commands:

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


## Cryptography options

![cryptography options](images/cryptography-options.png)


## Controlling access by roles

I generated this controller:

```
package dio.spring.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WelcomeController {
    @GetMapping
    @PreAuthorize("hasAnyRole('managers', 'users')")
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }

    @PreAuthorize("hasAnyRole('users')")
    @GetMapping("/users")
    public String users() {
        return "Authorized user";
    }

    @PreAuthorize("hasAnyRole('managers')")
    @GetMapping("/managers")
    public String managers() {
        return "Authorized manager";
    }
}
```

And I have this `application.properties`:

```
server.port=8081
spring.security.user.name=user
spring.security.user.password=password
spring.security.user.roles=users
```

I only eddited this two files until now. Until now, with the user "user":

- I can access the routes:
  - /
  - /users
- I get an HTTP error status 403 when I access:
  - /managers

I made [this video](https://youtu.be/H-jyFpcq9LQ) about this question.


## WebSecurityConfigurerAdapter

Teacher teached about `WebSecurityConfigurerAdapter` but now (is not the guilty of the teacher) is deprecated as you can see [in this link](https://cursos.alura.com.br/forum/topico-websecurityconfigureradapter-deprecated-222772). I can't import a class that is used in inheritance to complete the testing process. In this link you can see what to do as alternative, but I did not test this suggestion.

In summary, teacher transfered the access control based in roles after the user is authenticated from the controller for a specific config class. In particular, to me is better to mantain this configuration in the controller. Is a simple annotation and you wiil not need to open another file. You will need to **this part** of configuration only accessing the controller file.

To be more clear, please see the file eddited by teacher. He eddited only the first method:

![images/WebSecurityConfig-java.png](images/WebSecurityConfig-java.png)

Teacher teached us that you can specify the HTTP method allowed:

![images/expliciting-HTTP-method.png](images/expliciting-HTTP-method.png)

In the like 20 you can see how to specity a specific HTTP method and in the line 19 you can see how to specify a route that do not need authentication (`permitAll`).


## Using database in authentication process

I added `Spring Data JPA` and `H2 database` as dependencies in `pom.xml` this way under `dependencies` tag:

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```

Obviously, after I ran this command:

```
mvn install
```

I created the `User` model and the `UserRepository` in the `models` and the `repositories` directories with this content:

```
package dio.spring.security.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    public User(){

    }
    public User(String username){
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
```

```
package dio.spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dio.spring.security.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username= (:username)")
    public User findByUsername(@Param("username") String username);
}
```

As you can saw, User.java model is a `class` and UserRepository.java repository is an `interface`.

I am not tested all things of the class related to configure an authentication using a database. I explain better in [this video](https://youtu.be/3PbhwCuIxv0).
