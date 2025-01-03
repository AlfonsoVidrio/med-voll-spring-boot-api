package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private boolean active;

    public Doctor(DoctorData doctorData) {
        this.name = doctorData.name();
        this.email = doctorData.email();
        this.phone = doctorData.phone();
        this.document = doctorData.document();
        this.specialty = doctorData.specialty();
        this.address = new Address(doctorData.address());
        this.active = true;
    }

    public void updateDoctor(UpdateDoctorData updateDoctorData) {
        if (!this.id.equals(updateDoctorData.id())) {
            throw new IllegalArgumentException("Doctor id does not match");
        }
        if (updateDoctorData.name() != null) {
            this.name = updateDoctorData.name();
        }
        if (updateDoctorData.document() != null) {
            this.document = updateDoctorData.document();
        }
        if (updateDoctorData.address() != null) {
            this.address.updateAddress(updateDoctorData.address());
        }
    }

    public void desactiveDoctor() {
        this.active = false;
    }
}
