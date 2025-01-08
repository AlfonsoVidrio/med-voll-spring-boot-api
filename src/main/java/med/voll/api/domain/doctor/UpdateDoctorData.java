package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.address.AddressUpdateData;

public record UpdateDoctorData(
        @NotNull
        Long id,
        String name,
        String document,
        AddressUpdateData address
) {
}
