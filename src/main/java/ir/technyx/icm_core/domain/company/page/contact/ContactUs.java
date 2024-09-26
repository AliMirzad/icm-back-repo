package ir.technyx.icm_core.domain.company.page.contact;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_contact_us")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs extends BaseEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company")
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contactUs")
    private List<ContactItem> contactItems;

    @Embedded
    private RegistrationInfo registrationInfo;

}
