package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record UpdateDoctorData(
        @NotNull
        Long id,
        String name,
        String document,
        AddressData address
) {
}
