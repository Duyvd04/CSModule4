package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> findAll();
}
