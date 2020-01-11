package org.softserve.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

public class DtoConverter {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Convert Data Transfer Object to JSON String
     * @param dtoClass
     * @return
     */
    public static String dtoToJsonString(Object dtoClass) {
        ObjectMapper mapper = new ObjectMapper();
        String result = Strings.EMPTY;
        try {
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(dtoClass);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    /**
     * Convert Data Transfer Object to Pretty JSON String
     * @param dtoClass
     * @return
     */
    public static String dtoToPrettyJsonString(Object dtoClass) {
        String result = Strings.EMPTY;
        ObjectMapper mapper = new ObjectMapper();
        try{
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoClass);
        }catch (JsonProcessingException e){
            LOGGER.error(e.getMessage());
        }
        return result;
    }

}
