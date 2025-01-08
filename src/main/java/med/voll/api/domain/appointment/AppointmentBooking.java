package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.validations.QueryValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentBooking {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private List<QueryValidator> validators;

    public AppointmentDetailsData reserve(AppointmentData appointmentData) {

        if(!patientRepository.existsById(appointmentData.patientId())){
            throw new IllegalArgumentException("Patient not found with id: " + appointmentData.patientId());
        }

        if(appointmentData.doctorId() != null && !doctorRepository.existsById(appointmentData.doctorId())){
            throw new IllegalArgumentException("Doctor not found with id: " + appointmentData.doctorId());
        }

        validators.forEach(v -> v.validate(appointmentData));

        var doctor = selectDoctor(appointmentData);
        if(doctor == null){
            throw new ValidateException("Doctor not found");
        }
        var patient = patientRepository.findById(appointmentData.patientId()).get();
        var query = new Appointment(null, doctor, patient, appointmentData.date());
        appointmentRepository.save(query);
        return new AppointmentDetailsData(query);
    }

    private Doctor selectDoctor(AppointmentData appointmentData) {
        if(appointmentData.doctorId() != null) {
            return doctorRepository.getReferenceById(appointmentData.doctorId());
        }
        if (appointmentData.specialty() == null) {;
            throw new IllegalArgumentException("Specialty is required");
        }
        return doctorRepository.selectRandomAvailableDoctorOnDate(appointmentData.specialty(), appointmentData.date());
    }
}
