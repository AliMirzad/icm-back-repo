package ir.technyx.icm_core.domain.company;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_seo", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company", "meta_keyword", "active"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seo extends BaseEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company")
    private Company company;

    @Column(name = "title")
    private String title;

    @Column(name = "meta_keyword")
    private String metaKeyword;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Embedded
    private RegistrationInfo registrationInfo;

}
