package edu.university_connect.model.contract.response;


import edu.university_connect.model.enums.AppStatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private String message;
    private String code;
    private boolean status;
    private T data;

    private Error errors;

    public ApiResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public ApiResponse(String message, T data){
        setMessage(message);
        setData(data);
    }

    public String getCode() {
        if(code==null && status){
            code= AppStatusCode.S20000.name();
        }
        return code;
    }
    public void setResponseData(T response) {
        data=response;
        status=true;
        code=AppStatusCode.S20000.name();
    }
    public void setResponseData(T response,AppStatusCode appStatusCode) {
        data=response;
        status=true;
        code=appStatusCode.name();
    }
}