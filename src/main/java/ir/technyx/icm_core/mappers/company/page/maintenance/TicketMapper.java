package ir.technyx.icm_core.mappers.company.page.maintenance;

import ir.technyx.icm_core.domain.company.maintenance.Ticket;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketUpdatableDto;
import ir.technyx.icm_core.mappers.common.ManagementTypeMapper;
import ir.technyx.icm_core.mappers.company.CompanyMapper;
import ir.technyx.icm_core.utils.UserUtility;

import java.util.List;
import java.util.stream.Collectors;

public interface TicketMapper {

    static Ticket toTicket(Long id) {
        return new Ticket(id);
    }

    static Ticket toTicket(ReqTicketDto reqTicketDto) {
        return new Ticket(
                CompanyMapper.toCompany(reqTicketDto.getCompanyCode()),
                ManagementTypeMapper.toManagementType(reqTicketDto.getTicketTypeId()),
                reqTicketDto.getTitle(),
                reqTicketDto.getDescription(),
                UserUtility.getCurrentRegistrationInfo()
        );
    }

    static Ticket toTicket(ReqTicketUpdatableDto reqTicketUpdatableDto) {
        return new Ticket(
                reqTicketUpdatableDto.getId(),
                CompanyMapper.toCompany(reqTicketUpdatableDto.getCompanyCode()),
                ManagementTypeMapper.toManagementType(reqTicketUpdatableDto.getTicketTypeId()),
                reqTicketUpdatableDto.getTitle(),
                reqTicketUpdatableDto.getDescription(),
                UserUtility.getCurrentRegistrationInfo()
        );
    }

    static ResTicketDto toResTicketDto(Ticket ticket) {
        return new ResTicketDto(
                ticket.getId(),
                ticket.getCode(),
                ticket.getTitle(),
                ticket.getStateType().getId()
        );
    }

    static List<ResTicketUpdatableDto> toResTicketUpdatableDto(List<Ticket> tickets) {
        return tickets.stream().map(TicketMapper::toResTicketUpdatableDto).collect(Collectors.toList());
    }

    static ResTicketUpdatableDto toResTicketUpdatableDto(Ticket ticket) {
        return new ResTicketUpdatableDto(
                ticket.getId(),
                ticket.getCode(),
                ticket.getCompany().getCode(),
                ticket.getTicketType().getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStateType().getId()
        );
    }

}
