package miu.waa.project1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String title;
    private String description;
    private int rate;
    private String comment;
    private String email;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
