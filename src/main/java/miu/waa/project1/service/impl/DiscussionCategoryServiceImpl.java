package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.DiscussionCategory;
import miu.waa.project1.repository.DiscussionCategoryRepository;
import miu.waa.project1.service.DiscussionCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscussionCategoryServiceImpl implements DiscussionCategoryService {
	private final DiscussionCategoryRepository categoryRepository;

	@Override
	public List<DiscussionCategory> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public DiscussionCategory createOne(DiscussionCategory u) {
		return categoryRepository.save(u);
	}

	@Override
	public Optional<DiscussionCategory> updateOne(Long id, DiscussionCategory u) {
		DiscussionCategory item = categoryRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}

		item.setTitle(u.getTitle());
		item.setDiscussions(u.getDiscussions());


		return Optional.of(categoryRepository.save(item));
	}

	@Override
	public Optional<DiscussionCategory> deleteOne(Long id) {
		DiscussionCategory item = categoryRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		categoryRepository.deleteById(id);
		return Optional.of(item);
	}

	@Override
	public Optional<DiscussionCategory> findById(Long id) {
		DiscussionCategory item = categoryRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		return Optional.of(item);
	}
}
