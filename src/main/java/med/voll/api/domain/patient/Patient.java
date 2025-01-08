package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressUpdateData;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Embedded
    private Address address;
    private boolean active;

    public Patient(PatientData patientData) {
        this.active = true;
        this.name = patientData.name();
        this.email = patientData.email();
        this.phone = patientData.phone();
        this.document = patientData.document();
        this.address = new Address(patientData.address());
    }

    public void updatePatient(PatientUpdateData patientData) {
        if (patientData.name() != null) {
            this.name = patientData.name();
        }
        if (patientData.phone() != null) {
            this.phone = patientData.phone();
        }
        if (patientData.address() != null) {
            this.address.updateAddress(patientData.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
