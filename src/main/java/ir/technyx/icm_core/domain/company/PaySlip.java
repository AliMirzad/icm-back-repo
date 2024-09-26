package ir.technyx.icm_core.domain.company;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pay_slip", uniqueConstraints = {@UniqueConstraint(columnNames = {"url", "register_date"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlip extends BaseEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user")
    private User user;

    @Column(name = "url")
    private String url;

    @Embedded
    private RegistrationInfo registrationInfo;


}
