package me.mezesdimit.controller;

import me.mezesdimit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import me.mezesdimit.repository.UserRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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



    public static class AuthResponse {
        public int status;
        public String message;

        public AuthResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String allUser(){
        List<User> all = userRepository.findAll();
        String debug = "";
        Iterator<User> iterator = all.iterator();
        while(iterator.hasNext()){
            User next = iterator.next();
            debug += next.toString();
        }
        return debug;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        if(!userRepository.findByEmail(request.email).isPresent()) {
            return ResponseEntity.ok().body(
                    new AuthResponse(0,"User not found.")
            );
        }
        else{
            User user = userRepository.findByEmail(request.email).get();
            if (!passwordEncoder.matches(request.password, user.getPassword())) {
                return ResponseEntity.ok().body(
                        new AuthResponse(0,"Incorrect password.")
                );
            } else {
                return ResponseEntity.ok().body(
                        new AuthResponse(1,"Logged in successfully.")
                );
            }
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){

        if (!request.password.equals(request.passwordConfirm)) {
            return ResponseEntity.ok().body(
                    new AuthResponse(0,"Passwords do not match.")
            );
        }

        if (userRepository.findByEmail(request.email).isPresent()) {
            return ResponseEntity.ok().body(
                    new AuthResponse(0,"Email already in use.")
            );
        }

        User newUser = new User();
        newUser.setFirstName(request.firstName);
        newUser.setLastName(request.lastName);
        newUser.setEmail(request.email);
        newUser.setPassword(passwordEncoder.encode(request.password));

        userRepository.save(newUser);

        return ResponseEntity.ok(new AuthResponse(1, "User was created successfully"));

    }


}
