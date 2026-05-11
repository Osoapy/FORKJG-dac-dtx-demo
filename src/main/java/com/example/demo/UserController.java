package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserSagaService service;

    @PostMapping
    public String create(
            @RequestParam String name
    ) {

        service.createUser(name);

        return "Usuário criado";
    }
}