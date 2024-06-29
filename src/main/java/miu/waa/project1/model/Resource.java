package miu.waa.project1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import miu.waa.project1.common.ResourceType;

@Entity
@Setter
@Getter
public class Resource {
    @Id
    private Long id;
    private ResourceType type;
    private String name;
    private String description;
    private String url;

    @ManyToOne
    private User user;
}
