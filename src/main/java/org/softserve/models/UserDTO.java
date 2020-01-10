package org.softserve.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @JsonProperty(value = "name")
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;


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

    public Address getAddress() {
        return address;
    }

    public UserDTO setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public UserDTO setCompany(Company company) {
        this.company = company;
        return this;
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Test User Data\n");
        sb.append("name=" + getName() + "\n");
        sb.append("userName=" + getUsername() + "\n");
        sb.append("email=" + getEmail() + "\n");
        sb.append("address=" + getAddress() + "\n");
        sb.append("phone=" + getPhone() + "\n");
        sb.append("website=" + getWebsite() + "\n");
        sb.append("company=" + getCompany() + "\n");

        sb.append("*****************************");

        return sb.toString();
    }

    public static UserDTO createUser() {

        UserDTO user = new UserDTO();
        user.setName("David");
        user.setUsername("DavidJohnson");
        user.setEmail("test@email.com");

        Address address = new Address();
        address.setCity("London");
        address.setStreet("Blackwood Str");
        address.setSuite("TestSuite");
        address.setZipcode("AF 90009");
        user.setAddress(address);


        user.setPhone("0212896652");
        user.setWebsite("www.Johnson.com");

        Company company = new Company();
        company.setBs("Test BS");
        company.setName("Johnson & Johnson");
        company.setCatchPhrase("Test Phrase");
        user.setCompany(company);

        return user;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(phone, userDTO.phone) &&
                Objects.equals(website, userDTO.website) &&
                Objects.equals(address, userDTO.address) &&
                Objects.equals(company, userDTO.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username, email, phone, website, address, company);
    }
}
