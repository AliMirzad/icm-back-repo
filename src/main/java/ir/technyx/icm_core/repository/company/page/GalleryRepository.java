package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Optional<Gallery> findByCompanyId(Long companyId);
}
