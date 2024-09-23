package models;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserResponseModel {
    private Map<String,?> data;
    private Map<String,String> support;
}
