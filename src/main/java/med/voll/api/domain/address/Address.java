package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Address(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }

    /*public Address updateAddress(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        return this;
    }*/

    public Address updateAddress(AddressUpdateData address) {
        if (address.street() != null) {
            this.street = address.street();
        }
        if (address.district() != null) {
            this.district = address.district();
        }
        if (address.city() != null) {
            this.city = address.city();
        }
        if (address.number() != null) {
            this.number = address.number();
        }
        if (address.complement() != null) {
            this.complement = address.complement();
        }
        return this;
    }
}