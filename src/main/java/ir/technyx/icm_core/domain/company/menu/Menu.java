package ir.technyx.icm_core.domain.company.menu;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_menu", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company", "url"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @Column(name = "title")
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "priority")
    private float priority;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_use_type")
    private ManagementType useType;

    @Embedded
    private RegistrationInfo registrationInfo;


    public static Menu getInstanceById(Long id) {
        Menu menu = new Menu();
        menu.setId(id);
        return menu;
    }

    public Menu(Long id) {
        setId(id);
    }


    public Menu(Company company, String title, String url, String iconPath, boolean active) {
        this(null, company, title, url, iconPath, active, null);
    }

    public Menu(Long id, Company company, String title, String url, String iconPath, boolean active) {
        setId(id);
        setCompany(company);
        setTitle(title);
        setUrl(url);
        setIconPath(iconPath);
        setActive(active);
        setRegistrationInfo(null);
    }

    public Menu(Long id, Company company, String title, String url, String iconPath, boolean active, RegistrationInfo registrationInfo) {
        setId(id);
        setCompany(company);
        setTitle(title);
        setUrl(url);
        setIconPath(iconPath);
        setActive(active);
        setRegistrationInfo(registrationInfo);
    }

}
