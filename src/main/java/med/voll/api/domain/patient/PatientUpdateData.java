package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.address.AddressUpdateData;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressUpdateData address
) {
}
