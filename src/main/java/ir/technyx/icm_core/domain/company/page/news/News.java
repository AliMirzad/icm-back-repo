package ir.technyx.icm_core.domain.company.page.news;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_news")
@Getter
@Setter
@NoArgsConstructor
public class News extends BaseEntity {

    public static final String NEWS_PAGE_TYPE = "news";

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "news")
    private List<NewsItem> newsItems;

    @Column(name = "active")
    private boolean active;

    @Embedded
    private RegistrationInfo registrationInfo;

    public News(Long id) {
        setId(id);
    }

    public News(Company company, boolean active, RegistrationInfo registrationInfo) {
        this(company, null, active, registrationInfo);
    }

    public News(Company company, List<NewsItem> newsItems, boolean active, RegistrationInfo registrationInfo) {
        this(null, company, newsItems, active, registrationInfo);
    }

    public News(Long id, Company company, List<NewsItem> newsItems, boolean active, RegistrationInfo registrationInfo) {
        setId(id);
        setCompany(company);
        setNewsItems(newsItems);
        setActive(active);
        setRegistrationInfo(registrationInfo);
    }
}
