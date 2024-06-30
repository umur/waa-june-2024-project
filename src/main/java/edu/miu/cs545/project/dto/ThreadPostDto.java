package edu.miu.cs545.project.dto;


import edu.miu.cs545.project.model.entity.Category;
import edu.miu.cs545.project.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
public class ThreadPostDto {

    private Long id;
    private  String title;
    private LocalDateTime createdAt;
    private Long categoryId;
    private Long userId;
}
