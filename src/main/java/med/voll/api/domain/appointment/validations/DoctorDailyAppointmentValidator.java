package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorDailyAppointmentValidator implements QueryValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentData appointmentData) {
        var doctorHasAppointment = appointmentRepository.existsByDoctorIdAndDate(appointmentData.doctorId(), appointmentData.date());
        if (doctorHasAppointment) {
            throw new IllegalArgumentException("Doctor already has an appointment scheduled for this day");
        }
    }
}
