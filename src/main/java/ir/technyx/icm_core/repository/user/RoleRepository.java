package ir.technyx.icm_core.repository.user;

import ir.technyx.icm_core.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(@Param("name") String name);

    @Query("""
                select r from Role as r where r.name in (:names)
            """)
    List<Role> findAllByNames(@Param("names") String... names);

    Optional<List<Role>> findAllByNameStartingWith(String roleName);
}
