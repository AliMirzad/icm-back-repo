package ir.technyx.icm_core.service.company.page.maintenance;

import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketUpdatableDto;

import java.util.List;

public interface TicketService {
    ResTicketDto registerTicket(ReqTicketDto reqTicketDto);

    ResTicketUpdatableDto updateTicket(ReqTicketUpdatableDto reqTicketUpdatableDto);

    void deleteTicket(Long ticketId);

    void deleteAllTicketByCompanyId(Long companyId);

    ResTicketUpdatableDto getTicketById(Long ticketId);

    ResTicketUpdatableDto getTicketByCode(String code);

    List<ResTicketUpdatableDto> getAllTicketByCompanyId(Long companyId);

    ResFeedbackDto registerFeedback(ReqFeedbackDto reqFeedBackDto);

    ResFeedbackUpdatableDto updateFeedback(ReqFeedbackUpdatableDto reqFeedBackUpdatableDto);

    void deleteFeedback(Long feedBackId);

    void deleteAllFeedbackByTicketId(Long ticketId);

    ResFeedbackUpdatableDto getFeedbackById(Long feedBackId);

    List<ResFeedbackUpdatableDto> getAllFeedbackByTicketId(Long ticketId);

}
