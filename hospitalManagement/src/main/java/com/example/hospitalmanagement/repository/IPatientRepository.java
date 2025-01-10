package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.Doctor;
import com.example.hospitalmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

    public interface IPatientRepository extends JpaRepository<Patient, Long> {

    // Tìm bệnh nhân theo bác sĩ (dựa trên quan hệ Many-to-Many)
    List<Patient> findByDoctorsId(Long doctorId);

}
