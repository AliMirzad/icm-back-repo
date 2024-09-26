package ir.technyx.icm_core.domain.company.maintenance;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_ticket", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code"),
        @UniqueConstraint(columnNames = {"fk_company", "fk_ticket_type", "register_date"})
})
@Getter
@Setter
@NoArgsConstructor
public class Ticket extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", updatable = false)
    private Company company;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_ticket_type", updatable = false)
    private ManagementType ticketType;

    @Column(name = "code", updatable = false)
    private String code;

    @Column(name = "titile")
    private String title;

    @Column(name = "description", updatable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_state_type")
    private ManagementType stateType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ticket")
    private List<Feedback> feedBack;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Ticket(Long id) {
        setId(id);
    }

    public Ticket(Company company, ManagementType ticketType, String title, String description, RegistrationInfo registrationInfo) {
        this(null, company, ticketType, title, description, registrationInfo);
    }

    public Ticket(Long id, Company company, ManagementType ticketType, String title, String description, RegistrationInfo registrationInfo) {
        setId(id);
        setCompany(company);
        setTicketType(ticketType);
        setTitle(title);
        setDescription(description);
        setRegistrationInfo(registrationInfo);
    }


}
