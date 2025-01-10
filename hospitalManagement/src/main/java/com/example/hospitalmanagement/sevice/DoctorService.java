package com.example.hospitalmanagement.sevice;

import com.example.hospitalmanagement.model.Doctor;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.repository.IDoctorRepository;
import com.example.hospitalmanagement.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private IDoctorRepository doctorRepository;
    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public Doctor getDoctorByUsername(String userName) {
        return doctorRepository.findByUserUserName(userName)
                .orElseThrow(() -> new RuntimeException("Doctor not found with username: " + userName));
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }


    @Override
    public List<Patient> getPatientsByDoctorId(Long id) {
        return patientRepository.findByDoctorsId(id);  // Giả sử bệnh nhân có quan hệ với bác sĩ qua field 'doctors'
    }

    @Override
    public void deletePatient(Long id) {
        // Tìm bệnh nhân trong cơ sở dữ liệu
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Xóa bệnh nhân khỏi tất cả bác sĩ trong danh sách của bệnh nhân
        for (Doctor doctor : patient.getDoctors()) {
            doctor.getPatients().remove(patient);  // Xóa bệnh nhân khỏi danh sách bác sĩ
            doctorRepository.save(doctor);  // Lưu lại thay đổi của bác sĩ
        }

        // Xóa bệnh nhân khỏi bảng Patient
        patientRepository.delete(patient);
    }


}

