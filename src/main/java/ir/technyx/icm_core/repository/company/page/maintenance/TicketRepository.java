package ir.technyx.icm_core.repository.company.page.maintenance;

import ir.technyx.icm_core.domain.company.maintenance.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    void deleteAllByCompanyId(@Param("companyId") Long companyId);

    Optional<Ticket> findTicketByCode(@Param("code") String code);

    Optional<List<Ticket>> findAllByCompanyId(@Param("companyId") Long companyId);
}
