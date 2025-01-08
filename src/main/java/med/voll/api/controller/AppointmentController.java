package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentBooking;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentBooking appointmentBooking;

    @PostMapping
    @Transactional
    public ResponseEntity<?> reserve(@RequestBody @Valid AppointmentData appointmentData) {
        var detailsAppointment = appointmentBooking.reserve(appointmentData);
        return ResponseEntity.ok(detailsAppointment);
    }
}