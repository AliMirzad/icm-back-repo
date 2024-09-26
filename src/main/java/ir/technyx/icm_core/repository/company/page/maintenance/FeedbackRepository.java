package ir.technyx.icm_core.repository.company.page.maintenance;

import ir.technyx.icm_core.domain.company.maintenance.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    void deleteAllByTicketId(@Param("ticketId") Long ticketId);

    Optional<List<Feedback>> findAllByTicketId(@Param("ticketId") Long ticketId);

}
