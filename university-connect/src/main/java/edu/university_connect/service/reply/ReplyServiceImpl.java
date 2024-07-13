package edu.university_connect.service.reply;

import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.domain.entity.discussionthread.Reply;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ReplyDtoMapper;
import edu.university_connect.model.contract.dto.ReplyDto;
import edu.university_connect.model.contract.request.discussionthread.PostReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.ReplyReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.ReplyRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.ReplyRepository;
import edu.university_connect.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final PostService postService;
    private final ContextUser contextUser;

    @Override
    public List<ReplyDto> getAll() {
        return replyRepository.findAllUnblockedReplies()
                .stream()
                .map(ReplyDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public List<ReplyDto> getByPostId(Long postId) {
        return replyRepository.findByPostIdAndReplyThreadNull(postId)
                .stream()
                .map(ReplyDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public List<ReplyDto> getByReplyId(Long replyId) {
        return replyRepository.findByReplyThreadId(replyId)
                .stream()
                .map(ReplyDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public ReplyDto getById(Long id) {
        Reply reply = getReply(id);
        return ReplyDtoMapper.MAPPER.entityToDto(reply);
    }

    @Override
    public boolean delete(Long id) {
        replyRepository.deleteById(getReply(id).getId());
        return true;
    }

    @Override
    public ReplyDto update(Long id, ReplyRequest replyRequest) {
        Reply reply = getReply(id);
        reply.setContent(replyRequest.getContent());
        Reply savedReply = replyRepository.save(reply);
        return ReplyDtoMapper.MAPPER.entityToDto(savedReply);
    }

    @Override
    public ReplyDto create(PostReplyRequest postReplyRequest) {
        Post post = postService.getPost(postReplyRequest.getPostId());
        Reply reply = ReplyDtoMapper.MAPPER.dtoToEntity(postReplyRequest);
        reply.setPost(post);
        reply.setUser(contextUser.getLoginUser().getUser());
        reply = replyRepository.save(reply);
        return ReplyDtoMapper.MAPPER.entityToDto(reply);
    }

    @Override
    public ReplyDto addReplyToReply(ReplyReplyRequest replyRequest) {
        Reply reply = ReplyDtoMapper.MAPPER.dtoToEntity(replyRequest);
        Reply replyThread = getReply(replyRequest.getToReplyId());
        reply.setReplyThread(replyThread);
        reply.setPost(replyThread.getPost());
        Reply savedReply = replyRepository.save(reply);

        return ReplyDtoMapper.MAPPER.entityToDto(savedReply);
    }

    private Reply getReply(Long id) {
        Optional<Reply> replyOpt = replyRepository.findUnblockedReply(id);
        if (replyOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "reply", "id " + id);
        }
        return replyOpt.get();
    }
}
