package ir.technyx.icm_core.repository.company.menu;

import ir.technyx.icm_core.domain.company.menu.SiteMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteMapRepository extends JpaRepository<SiteMap, Long> {
}
