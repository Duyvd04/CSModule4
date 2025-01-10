package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
