package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.model.MedicalRecord;
import com.example.hospitalmanagement.repository.IMedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicalRecordService  implements IMedicalRecordService {

    @Autowired
    private IMedicalRecordRepository  medicalRecordRepository;
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll() ;
    }
}
