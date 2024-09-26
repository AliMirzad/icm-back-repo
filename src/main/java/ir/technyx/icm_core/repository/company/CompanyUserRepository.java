package ir.technyx.icm_core.repository.company;

import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {

    Optional<CompanyUser> findCompanyUserByUser_Id(@Param("id") Long id);

    @Query(value = """
                    select cu.user from CompanyUser as cu where cu.company.code = 'ICM_1'
            """)
    Optional<List<User>> findAllIcmUser();


    @Query(value = """
                  select cu from CompanyUser  as cu where cu.company.code != 'ICM_1'
            """)
    Optional<List<CompanyUser>> findAllCompanyUsers();

    @Query(value = """
                    select cu from CompanyUser  as cu where cu.company.code != 'ICM_1' and cu.company.id = :id
            """)
    Optional<List<CompanyUser>> findAllByCompany_Id(@Param("id") Long id);


}
