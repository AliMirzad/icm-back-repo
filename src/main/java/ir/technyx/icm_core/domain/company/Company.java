package ir.technyx.icm_core.domain.company;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.Address;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.menu.Menu;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_company",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code", "name"}),
                @UniqueConstraint(columnNames = {"host_url"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {

    public static final String ICM_COMPANY_NAME = "ICM_COMPANY";
    public static final String ICM_COMPANY_CODE = "ICM_1";

    @Column(name = "code", nullable = false, updatable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "national_Code", nullable = false)
    private String nationalCode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_address")
    private Address address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "host_url", nullable = false, updatable = false)
    private String hostUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    private List<CompanyServiceInfo> companyServiceInfos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    private List<Menu> menus;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Company(@NotNull Long id) {
        setId(id);
    }

    public Company(String code) {
        setCode(code);
    }

    public Company(Long id, String code) {
        setId(id);
        setCode(code);
    }

    public Company(
            String name,
            String nationalCode,
            Address address,
            String phone,
            String email,
            String hostUrl,
            RegistrationInfo registrationInfo) {

        this(null, name, nationalCode, address, phone, email, hostUrl, registrationInfo);

    }

    public Company(
            Long id,
            String name,
            String nationalCode,
            Address address,
            String phone,
            String email,
            String hostUrl,
            RegistrationInfo registrationInfo
    ) {
        this(id, null, name, nationalCode, address, phone, email, hostUrl, null, null, registrationInfo);
    }

    public Company(
            Long id,
            String code,
            String name,
            String nationalCode,
            Address address,
            String phone,
            String email,
            String hostUrl,
            List<CompanyServiceInfo> companyServiceInfos,
            List<Menu> menus,
            RegistrationInfo registrationInfo
    ) {
        setId(id);
        setCode(code);
        setName(name);
        setNationalCode(nationalCode);
        setAddress(address);
        setPhone(phone);
        setEmail(email);
        setHostUrl(hostUrl);
        setCompanyServiceInfos(companyServiceInfos);
        setMenus(menus);
        setRegistrationInfo(registrationInfo);
    }

}
