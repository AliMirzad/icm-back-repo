package ir.technyx.icm_core.service.company.page.news;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.page.news.News;
import ir.technyx.icm_core.repository.company.page.NewsRepository;
import ir.technyx.icm_core.service.company.page.PageInfo;
import ir.technyx.icm_core.service.company.page.PageManagement;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsPageImpl implements PageManagement<PageInfo> {

    private final NewsRepository newsRepository;

    @Override
    public boolean support(PageInfo pageInfo) {
        return pageInfo.pageSubType().equals(News.NEWS_PAGE_TYPE);
    }

    @Override
    @Transactional
    public void addPage(PageInfo pageInfo) {
        Optional<News> newsOptional = newsRepository.findByCompanyId(pageInfo.companyId());
        if (newsOptional.isEmpty()) {
            News news = new News(new Company(pageInfo.companyId()), true, UserUtility.getCurrentRegistrationInfo());
            newsRepository.save(news);
        }
    }

    @Override
    public void activePage(PageInfo pageInfo) {
        changePageState(pageInfo.companyId(), true);
    }

    @Override
    public void deactivatePage(PageInfo pageInfo) {
        changePageState(pageInfo.companyId(), false);
    }

    private void changePageState(Long companyId, boolean active) {
        News news = newsRepository.findByCompanyId(companyId).orElseThrow(NullPointerException::new);
        news.setActive(active);
        newsRepository.save(news);
    }


}
