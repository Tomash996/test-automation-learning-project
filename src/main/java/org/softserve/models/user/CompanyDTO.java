package org.softserve.models.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CompanyDTO {

    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public CompanyDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public CompanyDTO setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
        return this;
    }

    public String getBs() {
        return bs;
    }

    public CompanyDTO setBs(String bs) {
        this.bs = bs;
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

        CompanyDTO that = (CompanyDTO) o;

        return new EqualsBuilder()
            .append(name, that.name)
            .append(catchPhrase, that.catchPhrase)
            .append(bs, that.bs)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(name)
            .append(catchPhrase)
            .append(bs)
            .toHashCode();
    }

}