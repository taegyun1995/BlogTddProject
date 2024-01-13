package tdd.blogProject.advice.success;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class SuccessResponse {

    private final int status;
    private final String code;
    private final String message;

    public SuccessResponse(SuccessCodeConst successCodeConst) {
        this.status = successCodeConst.getStatus();
        this.code = successCodeConst.getCode();
        this.message = successCodeConst.getMessage();
    }

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}