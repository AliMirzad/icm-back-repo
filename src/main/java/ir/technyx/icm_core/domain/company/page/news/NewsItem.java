package ir.technyx.icm_core.domain.company.page.news;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.Link;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_news_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsItem extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_news", nullable = false)
    private News news;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Link> links;

    @Embedded
    private RegistrationInfo registrationInfo;

}
