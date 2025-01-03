package med.voll.api.doctor;

public record ListDoctorData(
        Long id,
        String name,
        String specialty,
        String document,
        String email
) {
    public ListDoctorData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getSpecialty().toString(), doctor.getDocument(), doctor.getEmail());
    }
}
