package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.faq.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

    Optional<Faq> findByCompanyId(Long companyId);

    Optional<Faq> findByCompanyCode(String companyCode);

}
