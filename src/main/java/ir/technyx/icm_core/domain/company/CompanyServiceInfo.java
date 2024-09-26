package ir.technyx.icm_core.domain.company;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_company_service", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company", "title"}))
@Getter
@Setter
@NoArgsConstructor
public class CompanyServiceInfo extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @Column(name = "icon")
    private String icon;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    public CompanyServiceInfo(String title, String description) {
        this(null, title, description);
    }

    public CompanyServiceInfo(String icon, String title, String description) {
        this(null, icon, title, description);
    }


    public CompanyServiceInfo(Long id, String icon, String title, String description) {
        setId(id);
        setIcon(icon);
        setTitle(title);
        setDescription(description);
    }
}
