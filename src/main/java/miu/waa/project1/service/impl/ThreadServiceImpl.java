package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;

import miu.waa.project1.model.Discussion;
import miu.waa.project1.model.Thread;
import miu.waa.project1.repository.DiscussionRepository;
import miu.waa.project1.repository.ThreadRepository;
import miu.waa.project1.service.ThreadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {
	private final ThreadRepository threadRepository;
	private final DiscussionRepository discussionRepository;

	@Override
	public List<Thread> getAll() {
		return threadRepository.findAll();
	}

	@Override
	public Thread createOne(Thread u, Long discussionId) throws Exception {
		Discussion discussion =
				discussionRepository.findById(discussionId).orElseThrow(() -> new Exception("Discussion not found!"));
		u.setDiscussion(discussion);
		return threadRepository.save(u);
	}

	@Override
	public Optional<Thread> updateOne(Long id, Thread u) {
		Thread item = threadRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		item.setComment(u.getComment());
		return Optional.of(threadRepository.save(item));
	}

	@Override
	public Optional<Thread> deleteOne(Long id) {
		Thread item = threadRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		threadRepository.deleteById(id);
		return Optional.of(item);
	}

	@Override
	public Optional<Thread> findById(Long id) {
		Thread item = threadRepository.findById(id).orElse(null);
		if (item == null) {
			return Optional.empty();
		}
		return Optional.of(item);
	}
}
