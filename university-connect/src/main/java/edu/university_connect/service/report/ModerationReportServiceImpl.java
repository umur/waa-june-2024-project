package edu.university_connect.service.report;

import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.ModerationReport;
import edu.university_connect.domain.entity.User;
import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ModerationReportDtoMapper;
import edu.university_connect.mapper.PostDtoMapper;
import edu.university_connect.mapper.UserDtoMapper;
import edu.university_connect.model.contract.dto.ModerationReportDto;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.report.ModerationReportRequest;
import edu.university_connect.model.contract.request.report.ReportUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.ReportRepository;
import edu.university_connect.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModerationReportServiceImpl implements ModerationReportService {
    private final ReportRepository reportRepository;
    private final ContextUser contextUser;
    private final PostService postService;
    @Override
    public ModerationReportDto createReport(ModerationReportRequest moderationReportRequest) {
        Post post = postService.getPost(moderationReportRequest.getPost().getId());
        ModerationReport moderationReport = new ModerationReport();
        User user = contextUser.getLoginUser().getUser();
        moderationReport.setReporter(user);
        moderationReport.setPost(post);
        moderationReport.setReason(moderationReportRequest.getReason());
        ModerationReport savedModerationReport = reportRepository.save(moderationReport);
        ModerationReportDto moderationReportDto = prepareDto(savedModerationReport);
        return moderationReportDto;
    }

    public List<ModerationReportDto> getAll(){
        return reportRepository.findAll()
                .stream()
                .map(obj -> prepareDto(obj))
                .toList();
    }


    public ModerationReportDto update(Long id, ReportUpdateRequest reportUpdateRequest) {
        Optional<ModerationReport> reportOpt = reportRepository.findById(id);
        if (reportOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "id moderation report");
        }
        ModerationReport moderationReport = reportOpt.get();
        moderationReport.setActionTaken(reportUpdateRequest.isActionTaken());
        moderationReport.setActionComment(reportUpdateRequest.getActionComment());
        ModerationReport saved = reportRepository.save(moderationReport);
        return prepareDto(saved);
    }

    public boolean delete(Long id) {
        Optional<ModerationReport> reportOpt = reportRepository.findById(id);
        if (reportOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "id moderation report");
        }
        ModerationReport moderationReport = reportOpt.get();
        reportRepository.delete(moderationReport);
        return true;
    }

    private ModerationReportDto prepareDto(ModerationReport savedModerationReport) {
        savedModerationReport.setCreatedBy(contextUser.getLoginUser().getUsername());
        savedModerationReport.setCreatedAt(contextUser.getLoginUser().getUser().getCreatedAt());
        ModerationReportDto moderationReportDto = ModerationReportDtoMapper.MAPPER.entityToDto(savedModerationReport);
        return moderationReportDto;
    }
}
