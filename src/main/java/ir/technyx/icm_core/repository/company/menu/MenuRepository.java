package ir.technyx.icm_core.repository.company.menu;

import ir.technyx.icm_core.domain.company.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = """
             delete Menu as m where m.company.id = :companyId
            """)
    void deleteAllMenuByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
                    select m from Menu as m where m.company.code = :code
            """)
    Optional<List<Menu>> getAllMenuByCompanyCode(@Param("code") String code);

    @Deprecated
    @Query(value = """
                    select m.id from Menu as m where m.url = :url
            """)
    Optional<Long> getMenuByUrl(@Param("url") String url);

}
