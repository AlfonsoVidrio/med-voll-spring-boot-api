package med.voll.api.doctor;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.AddressData;

public record DoctorData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{10}")
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String document,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        AddressData address
) {
}
