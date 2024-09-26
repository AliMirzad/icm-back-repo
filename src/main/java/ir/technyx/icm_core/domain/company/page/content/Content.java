package ir.technyx.icm_core.domain.company.page.content;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.common.Media;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_content", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "meta_keyword", "active", "fk_content_type"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_page")
    private Page page;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_content_type")
    private ManagementType contentType;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "title")
    private String title;

    @Column(name = "meta_keyword")
    private String metaKeyword;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "active")
    private boolean active;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "cover_url")
    private String coverUrl;

    @Lob
    @Column(name = "body")
    private String body;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Media> mediaList;

    @Embedded
    private RegistrationInfo registrationInfo;

    public Content(Page page, ManagementType contentType, String iconPath, String title, String metaKeyword,
                   String metaDescription, boolean active, String slug, String description, Integer priority,
                   String coverUrl) {
        this(null, page, contentType, iconPath, title, metaKeyword, metaDescription, active, slug, description, priority, coverUrl);
    }

    public Content(Long id, Page page, ManagementType contentType, String iconPath, String title, String metaKeyword,
                   String metaDescription, boolean active, String slug, String description, Integer priority,
                   String coverUrl) {
        setId(id);
        setPage(page);
        setContentType(contentType);
        setIconPath(iconPath);
        setTitle(title);
        setMetaKeyword(metaKeyword);
        setMetaDescription(metaDescription);
        setActive(active);
        setSlug(slug);
        setDescription(description);
        setPriority(priority);
        setCoverUrl(coverUrl);
    }


}
