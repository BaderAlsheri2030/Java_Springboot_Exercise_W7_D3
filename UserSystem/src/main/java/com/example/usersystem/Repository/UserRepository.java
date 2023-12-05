package com.example.usersystem.Repository;

import com.example.usersystem.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);
    User findUserByUsernameAndPassword(String username,String password);
    User findUserByEmail(String email);
    @Query("select r from  User r where r.role =?1")
    List<User> roleFind(String role);
    @Query("SELECT a from User a where a.age >= ?1")
    List<User> ageFind(Integer age);
}
