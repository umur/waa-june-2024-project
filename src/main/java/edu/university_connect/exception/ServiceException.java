package edu.university_connect.exception;

import edu.university_connect.model.AppStatusCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final AppStatusCode statusCode;
    private final String[] args;

    public ServiceException(AppStatusCode statusCode, String[] args){
        this.statusCode = statusCode;
        this.args = args;
    }

    public static ServiceException of(AppStatusCode appStatusCode) {
        return new ServiceException(appStatusCode, new String[]{});
    }

    public static ServiceException of(AppStatusCode appStatusCode, String message) {
        return new ServiceException(appStatusCode, new String[]{message});
    }
    public static ServiceException of(AppStatusCode appStatusCode, String... message) {
        return new ServiceException(appStatusCode, message);
    }

}
