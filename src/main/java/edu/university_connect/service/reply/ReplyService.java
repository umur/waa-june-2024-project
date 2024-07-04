package edu.university_connect.service.reply;

import edu.university_connect.model.contract.dto.ReplyDto;
import edu.university_connect.model.contract.request.discussionthread.PostReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.ReplyReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.ReplyRequest;

import java.util.List;

public interface ReplyService {
    List<ReplyDto> getAll();

    List<ReplyDto> getByPostId(Long postId);

    List<ReplyDto> getByReplyId(Long replyId);

    ReplyDto getById(Long id);

    boolean delete(Long id);

    ReplyDto update(Long id, ReplyRequest replyRequest);

    ReplyDto create(PostReplyRequest postReplyRequest);

    ReplyDto addReplyToReply(ReplyReplyRequest replyRequest);
}
