package ir.technyx.icm_core.repository.rsa;

import ir.technyx.icm_core.domain.rsa.RsaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RsaRepository extends JpaRepository<RsaKey, Long> {

    @Query(value = "SELECT * FROM tb_rsa_key WHERE fk_company = ?1 and version= ?2 and active is true", nativeQuery = true)
    RsaKey findByCompanyId(Long companyId, Long version);
}
