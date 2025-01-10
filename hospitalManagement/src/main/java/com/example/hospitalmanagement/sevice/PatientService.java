package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.model.MedicalRecord;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.repository.IMedicalRecordRepository;
import com.example.hospitalmanagement.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository ipatientRepository;
    public List<Patient> findAll() {
        return ipatientRepository.findAll() ;
    }


}
