package universityconnect.service;

import universityconnect.domain.Discussion;
import universityconnect.domain.DiscussionSearchCriteria;
import universityconnect.dto.DiscussionDTO;

import org.springframework.data.domain.Pageable;
import universityconnect.dto.DiscussionSearchResponseDTO;

import java.util.List;

public interface DiscussionService {
    DiscussionDTO createDiscussion(DiscussionDTO discussionDTO);
    DiscussionDTO getDiscussionById(Long id);
    List<DiscussionDTO> getAllDiscussion();
    DiscussionDTO updateDiscussion(Long id, DiscussionDTO discussionDTO);
    void deleteDiscussion(Long id);
    Discussion findById(Long id);
    public DiscussionSearchResponseDTO searchDiscussions(DiscussionSearchCriteria criteria, Pageable page);
}
