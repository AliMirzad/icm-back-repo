package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Content, Long> {
}
