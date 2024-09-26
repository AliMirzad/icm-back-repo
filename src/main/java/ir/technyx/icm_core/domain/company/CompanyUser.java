package ir.technyx.icm_core.domain.company;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_company_user")
@Getter
@Setter
@NoArgsConstructor
public class CompanyUser extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @Embedded
    private RegistrationInfo registrationInfo;

    public CompanyUser(Company company, User user, RegistrationInfo registrationInfo) {
        this(null, company, user, registrationInfo);
    }

    public CompanyUser(Long companyId, Long userId, RegistrationInfo registrationInfo) {
        this(null, new Company(companyId), new User(userId), registrationInfo);
    }

    public CompanyUser(Long id, Company company, User user, RegistrationInfo registrationInfo) {
        setId(id);
        setCompany(company);
        setUser(user);
        setRegistrationInfo(registrationInfo);
    }

}
