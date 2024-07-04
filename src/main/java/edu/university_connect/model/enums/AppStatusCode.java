package edu.university_connect.model.enums;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public enum AppStatusCode {

    /*    First character will be S or E coresponding to success or error state
        Second character will have the value of HttpStatus Series value. The corresponding httpStatusCode should be of this series
        Rest can be of any value or order. Recommended to use logical or serial numeric characters for ease of tracking.
        description will correspond to messages_lang file entries and is not recommended to add entries here unless it is a unique scenario*/
    S20000("success", HttpStatus.OK),
    S20001("list.fetch.success", HttpStatus.OK),
    S20002("create.success", HttpStatus.CREATED),
    S20003("fetch.success", HttpStatus.OK),
    S20004("update.success", HttpStatus.OK),
    S20005("delete.success", HttpStatus.OK),
    S20006("enable.success", HttpStatus.OK),
    S20007("login.success", HttpStatus.OK),

    E40000("not.found", HttpStatus.NOT_FOUND),
    E40001("no.changes", HttpStatus.BAD_REQUEST),
    E40002("bad.values.supplied", HttpStatus.BAD_REQUEST),
    E40003("password.confirm.no.atch", HttpStatus.BAD_REQUEST),
    E40005("invalid.username.password", HttpStatus.BAD_REQUEST),
    E40006("already.exist", HttpStatus.BAD_REQUEST),
    E40007("token.expired", HttpStatus.UNAUTHORIZED),
    E40008("invalid.refresh.token", HttpStatus.UNAUTHORIZED),
    E40009("invalid.file.type", HttpStatus.BAD_REQUEST),
    E40010("unauthorized.for.resource", HttpStatus.UNAUTHORIZED),

    E50000("some.error.occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    E50001("fetch.error", HttpStatus.INTERNAL_SERVER_ERROR),

    E50002("list.fetch.error", HttpStatus.INTERNAL_SERVER_ERROR),
    E50003("create.error", HttpStatus.INTERNAL_SERVER_ERROR),
    E50004("update.error", HttpStatus.INTERNAL_SERVER_ERROR),
    E50005("delete.error", HttpStatus.INTERNAL_SERVER_ERROR),

    E50006("serialization.error", HttpStatus.INTERNAL_SERVER_ERROR),
    E50007("file.write.error", HttpStatus.INTERNAL_SERVER_ERROR),
    E50009("file.read.error", HttpStatus.INTERNAL_SERVER_ERROR),
    E50008("file.write.path.error", HttpStatus.INTERNAL_SERVER_ERROR);
    private final HttpStatus httpStatusCode;
    private final String description;

    private AppStatusCode(String description, HttpStatus httpStatusCode) {

        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }

    public String getDescription() {
        return this.description;
    }

    public HttpStatus getHttpStatusCode() {
        return this.httpStatusCode;
    }


    public static List<String> getEnumList() {
        List<String> enumValuesAsString = new ArrayList<>();
        for (AppStatusCode value : AppStatusCode.values()) {
            enumValuesAsString.add(value.toString());
        }
        return enumValuesAsString;
    }


}
