package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<DoctorResponseData> registerDoctor(@RequestBody @Valid DoctorData doctorData, UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = doctorRepository.save(new Doctor(doctorData));
        DoctorResponseData doctorResponseData = createDoctorResponseData(doctor);
        URI url = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(doctorResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorData>> listDoctors(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(doctorRepository.findByActiveTrue(pageable).map(ListDoctorData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorResponseData> updateDoctor(@RequestBody @Valid UpdateDoctorData updateDoctorData) {
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorData.id());
        doctor.updateDoctor(updateDoctorData);
        return ResponseEntity.ok(createDoctorResponseData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.desactiveDoctor();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorResponseData> getDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(createDoctorResponseData(doctor));
    }

    private DoctorResponseData createDoctorResponseData(Doctor doctor) {
        return new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getDocument(),
                new AddressData(doctor.getAddress().getStreet(),doctor.getAddress().getDistrict(),doctor.getAddress().getCity(), doctor.getAddress().getNumber(), doctor.getAddress().getComplement())
        );
    }

}