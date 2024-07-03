package universityconnect.dto;

import java.util.List;

public class DiscussionSearchResponseDTO {

    private List<DiscussionDTO> content;

    public List<DiscussionDTO> getContent() {
        return content;
    }

    public void setContent(List<DiscussionDTO> content) {
        this.content = content;
    }
}
