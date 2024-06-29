package miu.waa.project1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Logger {
    @Id
    private Long id;
    private String method;
    private String parameters;
    private String action;
    private String message;
    private int duration;
}
