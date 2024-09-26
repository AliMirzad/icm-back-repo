package ir.technyx.icm_core.mappers.company.page.maintenance;

import ir.technyx.icm_core.domain.company.maintenance.Feedback;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackUpdatableDto;
import ir.technyx.icm_core.utils.UserUtility;

import java.util.List;
import java.util.stream.Collectors;

public interface FeedbackMapper {

    static Feedback toFeedback(ReqFeedbackDto reqFeedBackDto) {
        return new Feedback(
                TicketMapper.toTicket(reqFeedBackDto.getTicketId()),
                reqFeedBackDto.getAnswer(),
                UserUtility.getCurrentRegistrationInfo()
        );
    }

    static Feedback toFeedback(ReqFeedbackUpdatableDto reqFeedbackUpdatableDto) {
        return new Feedback(
                reqFeedbackUpdatableDto.getId(),
                TicketMapper.toTicket(reqFeedbackUpdatableDto.getTicketId()),
                reqFeedbackUpdatableDto.getAnswer(),
                UserUtility.getCurrentRegistrationInfo()
        );
    }

    static ResFeedbackDto toResFeedbackDto(Feedback feedback) {
        return new ResFeedbackDto(feedback.getId());
    }

    static List<ResFeedbackUpdatableDto> toResFeedbackUpdatableDto(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackMapper::toResFeedbackUpdatableDto).collect(Collectors.toList());
    }

    static ResFeedbackUpdatableDto toResFeedbackUpdatableDto(Feedback feedback) {
        return new ResFeedbackUpdatableDto(
                feedback.getId(),
                feedback.getTicket().getId(),
                feedback.getAnswer()
        );

    }
}
