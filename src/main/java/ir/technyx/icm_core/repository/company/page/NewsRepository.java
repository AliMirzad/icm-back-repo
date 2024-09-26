package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByCompanyId(Long companyId);
}
