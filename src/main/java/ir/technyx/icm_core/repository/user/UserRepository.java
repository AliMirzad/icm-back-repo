package ir.technyx.icm_core.repository.user;

import ir.technyx.icm_core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


    @Query(value = """
                    select u from User as u
                    join UserAccess as ua on ua.user.id = u.id
                    join Role as r on r.id = ua.role.id
                    where r.name in :names
            """)
    Optional<List<User>> findAllByUserRole(@Param("names") String... names);

    @Query(value = """
                select u from User as u where u not in (select cu.user from CompanyUser as cu)
            """)
    Optional<List<User>> findAllWithOutCompanyUser();

}
