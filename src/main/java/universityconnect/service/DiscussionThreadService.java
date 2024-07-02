package universityconnect.service;

import universityconnect.domain.DiscussionThread;
import universityconnect.dto.DiscussionThreadDTO;

import java.util.List;

public interface DiscussionThreadService {
    DiscussionThreadDTO createDiscussionThread(DiscussionThreadDTO discussionThreadDTO);
    DiscussionThreadDTO getDiscussionThreadById(Long id);
    List<DiscussionThreadDTO> getAllDiscussionThread();
    DiscussionThreadDTO updateDiscussionThread(Long id, DiscussionThreadDTO discussionThreadDTO);
    void deleteDiscussionThread(Long id);
    DiscussionThread findById(Long id);
}
