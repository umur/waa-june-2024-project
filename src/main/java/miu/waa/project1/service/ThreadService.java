package miu.waa.project1.service;

import miu.waa.project1.model.Thread;
import java.util.List;
import java.util.Optional;

public interface ThreadService {
    List<Thread> getAll();
    Thread createOne(Thread thread, Long discussionId) throws Exception;
    Optional<Thread> updateOne(Long id, Thread thread);
    Optional<Thread> deleteOne(Long id);
    Optional<Thread> findById(Long id);
}
