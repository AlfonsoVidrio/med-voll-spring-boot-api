package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsData(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
    public AppointmentDetailsData(Appointment query) {
        this(query.getId(), query.getDoctor().getId(), query.getPatient().getId(), query.getDate());
    }
}
