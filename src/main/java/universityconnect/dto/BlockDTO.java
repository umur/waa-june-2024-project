package universityconnect.dto;

import lombok.Data;

@Data
public class BlockDTO {
    private Long id;
    private Long blockedUserId;
    private String blockedUserName;
    private Long blockerUserId;
    private String blockerUserName;

    public BlockDTO() {}

    public BlockDTO(Long id, Long blockedUserId, String blockedUserName, Long blockerUserId, String blockerUserName) {
        this.id = id;
        this.blockedUserId = blockedUserId;
        this.blockedUserName = blockedUserName;
        this.blockerUserId = blockerUserId;
        this.blockerUserName = blockerUserName;
    }
}

