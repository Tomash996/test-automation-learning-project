package org.softserve.models;

import org.softserve.utils.DtoConverter;

public class AbstractDTO {

    @Override
    public String toString() {
        return DtoConverter.dtoToJsonString(this);
    }

}
