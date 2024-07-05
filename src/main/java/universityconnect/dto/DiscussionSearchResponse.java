package universityconnect.dto;

import lombok.Data;

import java.util.List;

@Data
public class DiscussionSearchResponse {

    private List<DiscussionDTO> content;
    private int totalPages;
    private long totalElements;
    private int number;
    private int size;
    private boolean first;
    private boolean last;

}
