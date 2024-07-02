package miu.waa.project1.service;

import miu.waa.project1.model.DiscussionCategory;

import java.util.List;
import java.util.Optional;

public interface DiscussionCategoryService {
    List<DiscussionCategory> getAll();
    DiscussionCategory createOne(DiscussionCategory u);
    Optional<DiscussionCategory> updateOne(Long id, DiscussionCategory u);
    Optional<DiscussionCategory> deleteOne(Long id);
    Optional<DiscussionCategory> findById(Long id);
}
