package ir.technyx.icm_core.repository.common;

import ir.technyx.icm_core.domain.common.SystemProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemPropertiesRepository extends JpaRepository<SystemProperties, Long> {
    Optional<List<SystemProperties>> findAllByKey(String key);
}
