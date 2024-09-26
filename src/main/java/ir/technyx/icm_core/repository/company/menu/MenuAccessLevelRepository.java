package ir.technyx.icm_core.repository.company.menu;

import ir.technyx.icm_core.domain.company.menu.MenuAccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuAccessLevelRepository extends JpaRepository<MenuAccessLevel, Long> {

    @Query(value = """
              select m from MenuAccessLevel as m
              join CompanyUser c on c.user.id = m.userAccess.user.id
              where c.company.code = 'ICM_1'
            """)
    Optional<List<MenuAccessLevel>> findAllIcmMenu();

    @Query(value = """
                    select m from MenuAccessLevel as m
                    join CompanyUser c on c.user.id = m.userAccess.user.id
                    where c.company.code != 'ICM_1'
                    and c.company.id = :companyId
            """)
    Optional<List<MenuAccessLevel>> findAllMenuByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
                  select m from MenuAccessLevel as m
                  join CompanyUser c on c.user.id = m.userAccess.user.id
                  where c.company.code = 'ICM_1'
                  and m.userAccess.user.id = :userId
            """)
    Optional<List<MenuAccessLevel>> findAllIcmMenuByUserId(@Param("userId") Long userId);


    @Query(value = """
                            select m from MenuAccessLevel  as m
                            join CompanyUser  c on c.user.id = m.userAccess.user.id
                            where c.company.code = 'ICM_1'
                            and m.userAccess.user.id = :userId
                            and m.userAccess.role.id = :roleId
            """)
    Optional<List<MenuAccessLevel>> findAllIcmMenuByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);


    @Query(value = """
                  select m from MenuAccessLevel as m
                  join CompanyUser c on c.user.id = m.userAccess.user.id
                  where c.company.code != 'ICM_1'
                  and m.userAccess.user.id = :userId
            """)
    Optional<List<MenuAccessLevel>> findAllMenuByUserId(Long userId);

    @Query(value = """
                  select m from MenuAccessLevel as m
                  join CompanyUser c on c.user.id = m.userAccess.user.id
                  where c.company.code = 'ICM_1'
            """)
    Optional<List<MenuAccessLevel>> getAllIcmMenuAccessLevels();

    @Query(value = """
                    select m from MenuAccessLevel  as m
                    where m.userAccess.user.id = :userId and m.menu.url = :menuUrl
            """)
    Optional<MenuAccessLevel> getMenuAccessLevelByUserIdAndMenuUrl(@Param("userId") Long userId, String menuUrl);

}
