package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;

public interface QueryValidator {
     void validate(AppointmentData appointmentData);
}
