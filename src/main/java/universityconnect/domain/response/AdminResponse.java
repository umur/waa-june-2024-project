package universityconnect.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class AdminResponse {

    private Long id;
    private String username;
    private String email;
    private String address;
    private String department;
    private List<Long> surveyIds;

}
