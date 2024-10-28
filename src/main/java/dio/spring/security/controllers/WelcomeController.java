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