package org.softserve.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.softserve.utils.DtoConverter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractDTO {

    @Override
    public String toString() {
        return DtoConverter.dtoToJsonString(this);
    }

}
