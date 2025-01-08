package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements QueryValidator{
    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentData appointmentData) {
        var patientIsActive = patientRepository.findActiveById(appointmentData.patientId());
        if (!patientIsActive) {
            throw new IllegalArgumentException("Patient is not active");
        }

    }

}
