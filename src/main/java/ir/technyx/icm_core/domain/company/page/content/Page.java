package ir.technyx.icm_core.domain.company.page.content;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_page")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company")
    private Company company;

    @Column(name = "page_code", nullable = false, updatable = false)
    private String pageCode;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_page_type")
    private ManagementType pageType;

    @Column(name = "title")
    private String title;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "meta_keyword")
    private String metaKeyword;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "active")
    private boolean active;

    @Column(name = "slug")
    private String slug;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "page")
    private List<Content> contents;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Page(Long pageId) {
        setId(pageId);
    }

    public Page(Company company, ManagementType pageType, String title, String iconPath, String metaKeyword,
                String metaDescription, boolean active, String slug) {

        this(null, company, pageType, title, iconPath, metaKeyword, metaDescription, active, slug);
    }

    public Page(Long id, Company company, ManagementType pageType, String title, String iconPath, String metaKeyword,
                String metaDescription, boolean active, String slug) {

        setId(id);
        setId(null);
        setCompany(company);
        setPageType(pageType);
        setTitle(title);
        setIconPath(iconPath);
        setMetaKeyword(metaKeyword);
        setMetaDescription(metaDescription);
        setActive(active);
        setSlug(slug);
    }

}
