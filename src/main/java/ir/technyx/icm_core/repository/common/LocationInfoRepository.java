package ir.technyx.icm_core.repository.common;

import ir.technyx.icm_core.domain.common.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationInfoRepository extends JpaRepository<LocationInfo, Long> {

    Optional<LocationInfo> findByTitle(@Param("title") String title);
}
