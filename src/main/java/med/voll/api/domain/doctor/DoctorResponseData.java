package med.voll.api.doctor;

import med.voll.api.address.AddressData;

public record DoctorResponseData(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        AddressData address
) {
}
