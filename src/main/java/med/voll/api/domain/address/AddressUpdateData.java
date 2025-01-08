package med.voll.api.domain.address;

import jakarta.validation.constraints.NotNull;

public record AddressUpdateData(
        String street,
        String district,
        String city,
        String number,
        String complement
) {
}