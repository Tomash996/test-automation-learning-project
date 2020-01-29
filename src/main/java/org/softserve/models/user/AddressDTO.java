package org.softserve.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getSuite() {
        return suite;
    }

    public AddressDTO setSuite(String suite) {
        this.suite = suite;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public AddressDTO setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressDTO that = (AddressDTO) o;

        return new EqualsBuilder()
            .append(street, that.street)
            .append(suite, that.suite)
            .append(city, that.city)
            .append(zipcode, that.zipcode)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(street)
            .append(suite)
            .append(city)
            .append(zipcode)
            .toHashCode();
    }

}