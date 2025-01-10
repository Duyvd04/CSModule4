package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUserUserName(String userName);

}
