package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.model.Doctor;
import com.example.hospitalmanagement.model.Patient;

import java.util.List;


public interface IDoctorService {
    Doctor getDoctorByUsername(String userName);

    Doctor getDoctorById(Long id);  // Phương thức lấy bác sĩ theo id

    List<Patient> getPatientsByDoctorId(Long id);
    // Xóa bệnh nhân khỏi danh sách bác sĩ theo id của bệnh nhân
    void deletePatient(Long id);  // Tham số id là id của bệnh nhân


}


