package universityconnect.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String message;

    public ErrorMessage(){}

    public ErrorMessage(String message) {
        this.message = message;
    }

}
