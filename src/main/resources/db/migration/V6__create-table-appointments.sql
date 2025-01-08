CREATE TABLE appointments (
    id bigint NOT NULL AUTO_INCREMENT,
    doctor_id bigint NOT NULL,
    patient_id bigint NOT NULL,
    date datetime NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id)
);