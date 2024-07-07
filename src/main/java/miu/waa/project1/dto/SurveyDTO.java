package miu.waa.project1.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Setter
@Getter
public class SurveyDTO {
    private String title;
    private String description;
    private String comment;
    private int rate;
    private String name;
}
