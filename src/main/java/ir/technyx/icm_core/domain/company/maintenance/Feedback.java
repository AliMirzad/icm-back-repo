package ir.technyx.icm_core.domain.company.maintenance;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_feedback")
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_ticket", updatable = false)
    private Ticket ticket;

    @Column(name = "answer")
    private String answer;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Feedback(Ticket ticket, String answer, RegistrationInfo registrationInfo) {
        this(null, ticket, answer, registrationInfo);
    }

    public Feedback(Long id, Ticket ticket, String answer, RegistrationInfo registrationInfo) {
        setId(id);
        setTicket(ticket);
        setAnswer(answer);
        setRegistrationInfo(registrationInfo);
    }

}
