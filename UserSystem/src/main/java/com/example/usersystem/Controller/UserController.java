package com.example.usersystem.Controller;

import com.example.usersystem.Model.User;
import com.example.usersystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
       if (errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
       }

        service.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        service.updateUser(id,user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted");
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username, @PathVariable String password){
       User user = service.login(username,password);
        return ResponseEntity.status(HttpStatus.OK).body("logged in successfully: "+user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity searchByEmail(@PathVariable String email){
    return ResponseEntity.status(HttpStatus.OK).body(service.searchByEmail(email));
    }


    @GetMapping("/role/{role}")
    public ResponseEntity usersByRole(@PathVariable String role){
    return ResponseEntity.status(HttpStatus.OK).body(service.usersByRole(role));
    }


    @GetMapping("/age/{age}")
    public ResponseEntity usersByAge(@PathVariable Integer age){
    return ResponseEntity.status(HttpStatus.OK).body(service.usersByAge(age));
    }


}
