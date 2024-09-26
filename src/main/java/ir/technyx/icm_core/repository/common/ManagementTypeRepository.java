package ir.technyx.icm_core.repository.common;

import ir.technyx.icm_core.domain.common.ManagementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagementTypeRepository extends JpaRepository<ManagementType, Long> {

    Optional<List<ManagementType>> findAllByCode(String code);

    Optional<ManagementType> findByCodeAndSubType(String code, String subType);

}
