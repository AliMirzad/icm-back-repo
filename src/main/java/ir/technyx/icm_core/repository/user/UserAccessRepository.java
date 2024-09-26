package ir.technyx.icm_core.repository.user;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.domain.user.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

    Optional<List<UserAccess>> findByUser_Id(Long userId);


    @Query("""
               select u.role from UserAccess u where u.user.id = :userId
            """)
    Set<Role> findAllRoleByUser_Id(@Param("userId") Long userId);


    void deleteAllByUser_Id(@Param("userId") Long userId);

    @Query("""
            select u from UserAccess u where u.user.id = :userId and u.role.name in ('ROLE_ICM_ADMIN','ROLE_ICM_USER')
             """)
    List<UserAccess> getIcmUserRoles(Long userId);

    @Query("""
            select u from UserAccess u where u.user.id = :userId and u.role.name not in ('ROLE_ICM_ADMIN','ROLE_ICM_USER')
               """)
    List<UserAccess> getUserRoles(Long userId);

    Optional<UserAccess> findByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

}
