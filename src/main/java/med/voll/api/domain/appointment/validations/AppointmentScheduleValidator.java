package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class AppointmentScheduleValidator implements QueryValidator{

    public void validate(AppointmentData appointmentData) {
        var date = appointmentData.date();
        var sunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var scheduleBeforeOpening = date.getHour() < 7;
        var scheduleAfterClosing = date.getHour() > 18;
        if (sunday || scheduleBeforeOpening || scheduleAfterClosing) {
            throw new IllegalArgumentException("Invalid appointment schedule, please select a date between Monday and Saturday, from 7:00 to 18:00");
        }
    }
}
