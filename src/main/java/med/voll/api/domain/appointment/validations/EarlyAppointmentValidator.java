package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class EarlyAppointmentValidator implements QueryValidator {

    public void validate(AppointmentData appointmentData) {
        var dateAppointment = appointmentData.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dateAppointment).toMinutes();
        if (differenceInMinutes < 30) {
            throw new IllegalArgumentException("Invalid appointment schedule, please select a date at least 30 minutes from now");
        }
    }
}