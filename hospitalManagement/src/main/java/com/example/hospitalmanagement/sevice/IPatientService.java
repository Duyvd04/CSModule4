package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> findAll();
}
