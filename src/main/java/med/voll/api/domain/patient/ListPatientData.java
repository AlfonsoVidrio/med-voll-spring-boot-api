package med.voll.api.domain.patient;

public record ListPatientData(
        Long id,
        String name,
        String email,
        String document
) {
    public ListPatientData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getDocument());
    }
}
