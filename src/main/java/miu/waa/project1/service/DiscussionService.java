package miu.waa.project1.service;

import miu.waa.project1.model.Discussion;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DiscussionService {
    List<Discussion> getAll(Pageable pageable, String keyword);
    Discussion createOne(Discussion discussion, Long categoryId) throws Exception;
    Optional<Discussion> updateOne(Long id, Discussion discussion);
    Optional<Discussion> deleteOne(Long id);
    Optional<Discussion> findById(Long id);
}
