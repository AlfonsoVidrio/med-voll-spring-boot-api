package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;

public record DoctorResponseData(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        AddressData address
) {
}
