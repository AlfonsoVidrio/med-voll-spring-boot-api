package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements QueryValidator {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentData appointmentData) {
        if (appointmentData.doctorId() == null) {
            return;
        }
        var doctorIsActive = doctorRepository.findActiveById(appointmentData.doctorId());
        if (!doctorIsActive) {
            throw new IllegalArgumentException("Doctor is not active");
        }
    }
}
