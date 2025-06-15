package me.mezesdimit.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    public static class LoginRequest{
        public String email;
        public String password;

        @Override
        public String toString() {
            return "LoginRequest{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    public static class RegisterRequest{
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public String passwordConfirm;

        @Override
        public String toString() {
            return "RegisterRequest{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", passwordConfirm='" + passwordConfirm + '\'' +
                    '}';
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        System.out.println("/login reached");
        System.out.println(request.toString());
        return "reached";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        System.out.println("/register reached");
        System.out.println(request.toString());
        return "reached";
    }


}
