package ir.technyx.icm_core.domain.company.page;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.Media;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_gallery", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company"}))
@Getter
@Setter
@NoArgsConstructor
public class Gallery extends BaseEntity {

    public static final String GALLERY_PAGE_TYPE = "gallery";

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Media> mediaList;

    @Column(name = "active")
    private boolean active;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Gallery(Long id) {
        setId(id);
    }

    public Gallery(Company company, boolean active, RegistrationInfo registrationInfo) {
        this(company, null, active, registrationInfo);
    }

    public Gallery(Company company, List<Media> mediaList, boolean active, RegistrationInfo registrationInfo) {
        this(null, company, mediaList, active, registrationInfo);
    }

    public Gallery(Long id, Company company, List<Media> mediaList, boolean active, RegistrationInfo registrationInfo) {
        setId(id);
        setCompany(company);
        setMediaList(mediaList);
        setActive(active);
        setRegistrationInfo(registrationInfo);
    }


}
