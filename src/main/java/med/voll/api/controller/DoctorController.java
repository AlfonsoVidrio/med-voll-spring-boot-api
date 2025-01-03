package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public void registerDoctor(@RequestBody @Valid DoctorData doctorData) {
        doctorRepository.save(new Doctor(doctorData));
    }

    @GetMapping
    public Page<ListDoctorData> listDoctors(@PageableDefault(size = 5) Pageable pageable) {
        return doctorRepository.findByActiveTrue(pageable)
                .map(ListDoctorData::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorData updateDoctorData) {
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorData.id());
        doctor.updateDoctor(updateDoctorData);
    }

    // LOGICAL DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.desactiveDoctor();
    }

    /*
    DATABASE DELETE
    public void deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctorRepository.delete(doctor);
    }*/
}
