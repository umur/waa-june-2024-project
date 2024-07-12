package com.waa.project.security.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private String           message;
    private List<FieldError> errors;

    public Map<String, String> getErrorMap() {
        Map<String, String> errorMap = new HashMap<>();

        errors.forEach(fieldError -> errorMap.putIfAbsent(fieldError.getField(), fieldError.getError()));

        return errorMap;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldError {
        private String field;
        private String error;
    }
}

