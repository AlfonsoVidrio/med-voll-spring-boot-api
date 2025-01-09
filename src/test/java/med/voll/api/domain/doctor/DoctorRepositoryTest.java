package med.voll.api.domain.doctor;


import jakarta.persistence.EntityManager;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("should return null when the doctor searched exists but is not available on the date")
    void selectRandomAvailableDoctorOnDateScenario1() {
        // given o arrange
        var nextMondayAt10 = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate().atTime(10, 0);

        var doctor = registerDoctor("doctor1", "doctor1@gmail.com", "123456", "3781463146");
        var patient = registerPatient("pacient1", "pacient1@gmail.com", "271629", "3781122892");
        registerAppointment(doctor, patient, nextMondayAt10);
        // when o act
        var doctorAvailable = repository.selectRandomAvailableDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        // then o assert
        assertNull(doctorAvailable);
    }

    @Test
    @DisplayName("should return doctor when the doctor searched exists and is available on the date")
    void selectRandomAvailableDoctorOnDateScenario2() {
        // given o arrange
        var nextMondayAt10 = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate().atTime(10, 0);
        var doctor = registerDoctor("doctor1", "doctor1@gmail.com", "123456", "3781463146");
        // when o act
        var doctorAvailable = repository.selectRandomAvailableDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        // then o assert
        assertEquals(doctorAvailable, doctor);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String name, String email, String document, String phone) {
        var doctor = new Doctor(new DoctorData(
                name,
                email,
                document,
                phone,
                Specialty.CARDIOLOGIA,
                addressData()
        ));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String document, String phone) {
        var patient = new Patient(new PatientData(
                name,
                email,
                document,
                phone,
                addressData()
        ));
        em.persist(patient);
        return patient;
    }



    private DoctorData registerDoctorData() {
        return new DoctorData(
                "name",
                "email",
                "password",
                "123456789",
                Specialty.CARDIOLOGIA,
                addressData()
        );
    }

    private PatientData registerPatientData() {
        return new PatientData(
                "name",
                "email",
                "password",
                "123456789",
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "calle x",
                "distrito x",
                "ciudad x",
                "123",
                "1"
        );
    }
}