package com.example.hospitalmanagement.repository;


import com.example.hospitalmanagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUserName(String username);
}