package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientData patientData) {
        patientRepository.save(new Patient(patientData));
    }

    @GetMapping
    public Page<ListPatientData> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return patientRepository.findAllByActiveTrue(pageable).map(ListPatientData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateData patientUpdateData) {
        var patient = patientRepository.getReferenceById(patientUpdateData.id());
        patient.updatePatient(patientUpdateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete (@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
    }
}
