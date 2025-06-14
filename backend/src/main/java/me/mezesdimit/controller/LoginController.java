package me.mezesdimit.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    public static class LoginRequest{
        public String email;
        public String password;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        System.out.println("/login reached");
        return "reached";
    }
}
