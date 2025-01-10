package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.AppUser;
import com.example.hospitalmanagement.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByAppUser(AppUser appUser);
}