package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.DiscussionThread;
import universityconnect.dto.DiscussionThreadDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.DiscussionThreadMapper;
import universityconnect.repository.DiscussionThreadRepository;
import universityconnect.service.DiscussionService;
import universityconnect.service.DiscussionThreadService;
import universityconnect.service.UserService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscussionThreadServiceImpl implements DiscussionThreadService {
    private final DiscussionThreadRepository discussionThreadRepository;
    private final DiscussionThreadMapper discussionThreadMapper;
    private final UserService userService;
    private final DiscussionService discussionService;
    @Override
    public DiscussionThreadDTO createDiscussionThread(DiscussionThreadDTO discussionThreadDTO) {
        DiscussionThread thread = discussionThreadMapper.discussionThreadDTOToDiscussionThread(discussionThreadDTO);
        thread.setUser(userService.findById(discussionThreadDTO.getUserId()));
        thread.setDiscussion(discussionService.findById(discussionThreadDTO.getDiscussionId()));
        List<DiscussionThread> nested = new ArrayList<>();
        if(discussionThreadDTO.getNestedThreadIds()!=null){
            discussionThreadDTO.getNestedThreadIds().forEach(id->{
                nested.add(findById(id));
            });
        }
        thread.setNestedThreads(nested);
        DiscussionThread savedThread = discussionThreadRepository.save(thread);
        return discussionThreadMapper.discussionThreadToDiscussionThreadDTO(savedThread);
    }

    @Override
    public DiscussionThreadDTO getDiscussionThreadById(Long id) {
        DiscussionThread thread= discussionThreadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion Thread not found with id: "+id));
        return discussionThreadMapper.discussionThreadToDiscussionThreadDTO(thread);
    }

    @Override
    public List<DiscussionThreadDTO> getAllDiscussionThread() {
        return discussionThreadRepository.findAll().stream().map(discussionThreadMapper::discussionThreadToDiscussionThreadDTO).toList();
    }

    @Override
    public DiscussionThreadDTO updateDiscussionThread(Long id, DiscussionThreadDTO discussionThreadDTO) {
        DiscussionThread thread = discussionThreadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion Thread not found with id: "+id));
        thread.setComment(discussionThreadDTO.getComment());
        thread.setUser(userService.findById(discussionThreadDTO.getUserId()));
        thread.setDiscussion(discussionService.findById(discussionThreadDTO.getDiscussionId()));
        List<DiscussionThread> nested = new ArrayList<>();
        discussionThreadDTO.getNestedThreadIds().forEach(threadId-> nested.add(findById(threadId)));
        thread.setNestedThreads(nested);
        DiscussionThread savedThread = discussionThreadRepository.save(thread);
        return discussionThreadMapper.discussionThreadToDiscussionThreadDTO(savedThread);
    }

    @Override
    public void deleteDiscussionThread(Long id) {
        if(discussionThreadRepository.existsById(id)){
            discussionThreadRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("Discussion Thread not found with id: "+id);
        }
    }

    @Override
    public DiscussionThread findById(Long id) {
        return discussionThreadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion Thread not found with id: "+id));
    }
}
