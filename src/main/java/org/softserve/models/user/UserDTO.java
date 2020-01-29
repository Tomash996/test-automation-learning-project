package org.softserve.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.softserve.models.AbstractDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends AbstractDTO {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "name")
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressDTO address;
    private CompanyDTO company;

    public String getId() {
        return id;
    }

    public UserDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public UserDTO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public UserDTO setAddress(AddressDTO address) {
        this.address = address;
        return this;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public UserDTO setCompany(CompanyDTO company) {
        this.company = company;
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

        UserDTO userDTO = (UserDTO) o;
        return new EqualsBuilder()
                .append(id, userDTO.id)
                .append(name, userDTO.name)
                .append(username, userDTO.username)
                .append(email, userDTO.email)
                .append(phone, userDTO.phone)
                .append(website, userDTO.website)
                .append(address, userDTO.address)
                .append(company, userDTO.company)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(username)
                .append(email)
                .append(phone)
                .append(website)
                .append(address)
                .append(company)
                .toHashCode();
    }
}
