package ir.technyx.icm_core.domain.company.page.faq;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_faq", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company"}))
@Getter
@Setter
@NoArgsConstructor
public class Faq extends BaseEntity {

    public static final String FAQ_PAGE_TYPE = "faq";

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "faq")
    private List<AskedQuestion> askedQuestions;

    @Column(name = "active")
    private boolean active;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Faq(Long id) {
        setId(id);
    }

    public Faq(Company company, boolean active, RegistrationInfo registrationInfo) {
        this(company, null, active, registrationInfo);
    }

    public Faq(Company company, List<AskedQuestion> askedQuestions, boolean active, RegistrationInfo registrationInfo) {
        this(null, company, askedQuestions, active, registrationInfo);
    }

    public Faq(Long id, Company company, List<AskedQuestion> askedQuestions, boolean active, RegistrationInfo registrationInfo) {
        setId(id);
        setCompany(company);
        setAskedQuestions(askedQuestions);
        setActive(active);
        setRegistrationInfo(registrationInfo);
    }

}
