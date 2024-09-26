package ir.technyx.icm_core.service.company.page.maintenance;

import ir.technyx.icm_core.domain.company.maintenance.Feedback;
import ir.technyx.icm_core.domain.company.maintenance.Ticket;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketUpdatableDto;
import ir.technyx.icm_core.mappers.company.page.maintenance.FeedbackMapper;
import ir.technyx.icm_core.mappers.company.page.maintenance.TicketMapper;
import ir.technyx.icm_core.repository.common.ManagementTypeRepository;
import ir.technyx.icm_core.repository.company.page.maintenance.FeedbackRepository;
import ir.technyx.icm_core.repository.company.page.maintenance.TicketRepository;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final FeedbackRepository feedbackRepository;

    private final ManagementTypeRepository managementTypeRepository;

    @Override
    @Transactional
    public ResTicketDto registerTicket(ReqTicketDto reqTicketDto) {
        Ticket ticket = TicketMapper.toTicket(reqTicketDto);
        setTicketCode(ticket);
        ticketRepository.save(ticket);
        return TicketMapper.toResTicketDto(ticket);
    }

    private void setTicketCode(Ticket ticket) {
        String ticketTypeTitle = managementTypeRepository
                .findById(ticket.getTicketType().getId())
                .orElseThrow(NullPointerException::new).getTitle();
        String ticketCode = UserUtility.createTicketCode(ticket.getCompany().getCode(), ticketTypeTitle);
        ticket.setCode(ticketCode);
    }

    @Override
    @Transactional
    public ResTicketUpdatableDto updateTicket(ReqTicketUpdatableDto reqTicketUpdatableDto) {
        Ticket ticket = TicketMapper.toTicket(reqTicketUpdatableDto);
        ticketRepository.save(ticket);
        return TicketMapper.toResTicketUpdatableDto(ticket);
    }

    @Override
    @Transactional
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    @Transactional
    public void deleteAllTicketByCompanyId(Long companyId) {
        ticketRepository.deleteAllByCompanyId(companyId);
    }

    @Override
    @Transactional
    public ResTicketUpdatableDto getTicketById(Long ticketId) {
        return TicketMapper.toResTicketUpdatableDto(ticketRepository.findById(ticketId).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public ResTicketUpdatableDto getTicketByCode(String code) {
        return TicketMapper.toResTicketUpdatableDto(ticketRepository.findTicketByCode(code).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResTicketUpdatableDto> getAllTicketByCompanyId(Long companyId) {
        return TicketMapper.toResTicketUpdatableDto(ticketRepository.findAllByCompanyId(companyId).orElse(new ArrayList<>()));
    }

    @Override
    @Transactional
    public ResFeedbackDto registerFeedback(ReqFeedbackDto reqFeedBackDto) {
        Feedback feedback = FeedbackMapper.toFeedback(reqFeedBackDto);
        feedbackRepository.save(feedback);
        return FeedbackMapper.toResFeedbackDto(feedback);
    }

    @Override
    @Transactional
    public ResFeedbackUpdatableDto updateFeedback(ReqFeedbackUpdatableDto reqFeedBackUpdatableDto) {
        Feedback feedback = FeedbackMapper.toFeedback(reqFeedBackUpdatableDto);
        feedbackRepository.save(feedback);
        return FeedbackMapper.toResFeedbackUpdatableDto(feedback);
    }

    @Override
    @Transactional
    public void deleteFeedback(Long feedBackId) {
        feedbackRepository.deleteById(feedBackId);
    }

    @Override
    @Transactional
    public void deleteAllFeedbackByTicketId(Long ticketId) {
        feedbackRepository.deleteAllByTicketId(ticketId);
    }

    @Override
    @Transactional
    public ResFeedbackUpdatableDto getFeedbackById(Long feedBackId) {
        return FeedbackMapper.toResFeedbackUpdatableDto(feedbackRepository
                .findById(feedBackId).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResFeedbackUpdatableDto> getAllFeedbackByTicketId(Long ticketId) {
        return FeedbackMapper.toResFeedbackUpdatableDto(feedbackRepository
                .findAllByTicketId(ticketId).orElse(new ArrayList<>()));
    }

}
