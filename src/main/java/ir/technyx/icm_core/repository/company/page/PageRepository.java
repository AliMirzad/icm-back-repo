package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.content.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findPageByPageCode(@Param("pageCode") String pageCode);

    @Query(value = """
                        select p from Page as p
                        where p.company.code = :companyCode
            """)
    Optional<List<Page>> findAllByCompanyCode(@Param("companyCode") String companyCode);

    @Query(value = """
                        select p from  Page as p
                        where p.company.id = :companyId
                        and p.pageType.subType = :pageTypeCode
""")
    Optional<Page> findByCompanyIdAndPageSubType(@Param("companyId") Long companyId,String pageTypeCode);

    @Query(value = """
                    delete from Page as p
                    where p.company.code = :companyCode
            """)
    void deleteAllByCompanyCode(@Param("companyCode") String companyCode);

}
