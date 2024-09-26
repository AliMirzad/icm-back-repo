package ir.technyx.icm_core.repository.company.page;

import ir.technyx.icm_core.domain.company.page.faq.AskedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AskedQuestionRepository extends JpaRepository<AskedQuestion, Long> {

    Optional<List<AskedQuestion>> findAllByFaqId(@Param("faqId") long faqId);

    void deleteAllByFaqId(@Param("faqId") long faqId);

}
