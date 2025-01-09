package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentBooking;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentDetailsData;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<AppointmentData> appointmentDataJsonTester;
    @Autowired
    private JacksonTester<AppointmentDetailsData> appointmentDetailsDataJsonTester;
    @MockBean
    private AppointmentBooking reservation;

    @Test
    @DisplayName("should return status 400 when the appointment data is empty")
    @WithMockUser// This annotation is used to simulate a user logged in
    void reserve_scenario1() throws Exception {
        var response = mvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("should return status 200 when the appointment data is valid")
    @WithMockUser// This annotation is used to simulate a user logged in
    void reserve_scenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var detailsData = new AppointmentDetailsData(null, 2l, 5l, date);
        when(reservation.reserve(any())).thenReturn(detailsData);

        var response = mvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentDataJsonTester.write(
                                new AppointmentData(2l, 5l, date, specialty)
                        ).getJson()))

                .andReturn().getResponse();

        var expectedJson = appointmentDetailsDataJsonTester.write(
                new AppointmentDetailsData(null, 2l, 5l, date)
        ).getJson();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedJson, response.getContentAsString());
    }
}