package ir.technyx.icm_core.repository.common;

import ir.technyx.icm_core.domain.common.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
