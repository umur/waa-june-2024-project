package miu.waa.project1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    private Long id;
    private LocalDate date;
    private String title;
    private String description;
    private int rate;
    private String comment;
    private String email;
    private String name;
}
