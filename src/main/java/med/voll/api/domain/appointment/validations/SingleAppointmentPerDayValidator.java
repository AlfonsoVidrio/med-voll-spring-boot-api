package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingleAppointmentPerDayValidator implements QueryValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentData appointmentData) {
        var firstAppointment = appointmentData.date().withHour(7);
        var lastAppointment = appointmentData.date().withHour(18);
        boolean patientHasAnotherAppointment = appointmentRepository.existsByPatientIdAndDateBetween(appointmentData.patientId(), firstAppointment, lastAppointment);
        System.out.println("patientHasAnotherAppointment = " + patientHasAnotherAppointment);
        if (patientHasAnotherAppointment) {
            throw new IllegalArgumentException("Patient already has an appointment scheduled for this day");
        }
    }
}