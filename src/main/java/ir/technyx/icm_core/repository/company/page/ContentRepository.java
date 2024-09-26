package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Optional<List<Content>> findAllByPageId(@Param("pageId") Long pageId);

    Optional<List<Content>> findAllByPage_pageCode(@Param("pageCode") String pageCode);

    void deleteAllByPageId(@Param("pageId") Long pageId);

    void deleteAllByPage_pageCode(@Param("pageCode") String pageCode);

}
