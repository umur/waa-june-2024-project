package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Discussion;
import miu.waa.project1.model.DiscussionCategory;
import miu.waa.project1.repository.DiscussionCategoryRepository;
import miu.waa.project1.repository.DiscussionRepository;
import miu.waa.project1.service.DiscussionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscussionServiceImpl implements DiscussionService {
	private final DiscussionRepository discussionRepository;
	private final DiscussionCategoryRepository categoryRepository;

	@Override
	public List<Discussion> getAll(Pageable pageable, String keyword) {
		return discussionRepository.findAll();
	}

	@Override
	public Discussion createOne(Discussion u, Long categoryId) throws Exception {
		DiscussionCategory category =
				categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found!"));
		u.setCategory(category);
		return discussionRepository.save(u);
	}

	@Override
	public Optional<Discussion> updateOne(Long id, Discussion u) {
		Discussion item = discussionRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		item.setContent(u.getContent());
		return Optional.of(discussionRepository.save(item));
	}

	@Override
	public Optional<Discussion> deleteOne(Long id) {
		Discussion item = discussionRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		discussionRepository.deleteById(id);
		return Optional.of(item);
	}

	@Override
	public Optional<Discussion> findById(Long id) {
		Discussion item = discussionRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		return Optional.of(item);
	}
}
