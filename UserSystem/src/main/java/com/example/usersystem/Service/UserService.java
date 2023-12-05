package com.example.usersystem.Service;

import com.example.usersystem.Api.ApiException;
import com.example.usersystem.Model.User;
import com.example.usersystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;


    public List<User> getUsers(){
        return repository.findAll();
    }


    public void addUser(User user){
    repository.save(user);
    }

    public void updateUser(Integer id, User user){
        User user1 = repository.findUserById(id);
        if (user1 == null){
            throw new ApiException("Invalid id or user not found");
        }
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setRole(user.getRole());
        repository.save(user1);
    }


    public void deleteUser(Integer id){
        User user = repository.findUserById(id);
        if (user == null){
            throw new ApiException("Invalid id or user not found");
        }
        repository.delete(user);
    }

    public User login(String username,String password){
        User user = repository.findUserByUsernameAndPassword(username,password);
        if (user == null){
            throw new ApiException("wrong username or password");
        }
        return user;
    }

    public User searchByEmail(String email){
        User user = repository.findUserByEmail(email);
        if (user == null){
            throw new ApiException("Invalid email or user doesn't exist");
        }
        return user;
    }


    public List<User> usersByRole(String role){
        List<User> users = repository.roleFind(role);
        if (users.isEmpty()){
            throw new ApiException("Invalid role or list is empty");
        }
        return users;
    }


    public List<User> usersByAge(Integer age){
        List<User>  users = repository.ageFind(age);
        if (users.isEmpty()){
            throw new ApiException("Invalid input or no users exist with this age");
        }
        return users;
    }


}
