package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.model.Doctor;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.sevice.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class doctorController {
    @Autowired
    private IDoctorService idoctorService;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public String getDoctorProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doctor = idoctorService.getDoctorByUsername(username);
        if (doctor == null) {
            // Nếu bác sĩ không tìm thấy, có thể trả về trang lỗi
            return "error";
        }

        model.addAttribute("doctor", doctor);

        return "list";  // Thymeleaf template will use 'doctor' attribute here
    }
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}/patients")
    public String getPatientsByDoctorId(@PathVariable Long id, Model model) {
        List<Patient> patients = idoctorService.getPatientsByDoctorId(id);
        Doctor doctor = idoctorService.getDoctorById(id);  // Lấy thông tin bác sĩ
        model.addAttribute("doctor", doctor);
        model.addAttribute("patients", patients);
        return "patientList";  // Chuyển đến view hiển thị danh sách bệnh nhân
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/delete")
    public String deletePatient(@RequestParam("id") Long id) {
        try {
            // Gọi phương thức xóa bệnh nhân
            idoctorService.deletePatient(id);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi khi xóa
            return "error";  // Trả về trang lỗi nếu không xóa được
        }
        // Chuyển hướng về danh sách bệnh nhân của bác sĩ, đảm bảo truyền id vào URL
        return "redirect:/doctor/" + id + "/patients";  // Truyền id bác sĩ vào URL
    }






    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/create")
    public String viewCreate() {
        return "create";
    }
}
