package ir.technyx.icm_core.service.company.page.faq;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.page.faq.Faq;
import ir.technyx.icm_core.repository.company.page.FaqRepository;
import ir.technyx.icm_core.service.company.page.PageInfo;
import ir.technyx.icm_core.service.company.page.PageManagement;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FaqPageImpl implements PageManagement<PageInfo> {

    private final FaqRepository faqRepository;

    @Override
    public boolean support(PageInfo pageInfo) {
        return pageInfo.pageSubType().equals(Faq.FAQ_PAGE_TYPE);
    }

    @Override
    @Transactional
    public void addPage(PageInfo pageInfo) {
        Optional<Faq> faqOptional = faqRepository.findByCompanyId(pageInfo.companyId());
        if (faqOptional.isEmpty()) {
            Faq faq = new Faq(new Company(pageInfo.companyId()), true, UserUtility.getCurrentRegistrationInfo());
            faqRepository.save(faq);
        }
    }

    @Override
    @Transactional
    public void activePage(PageInfo pageInfo) {
        changePageState(pageInfo.companyId(), true);
    }

    @Override
    @Transactional
    public void deactivatePage(PageInfo pageInfo) {
        changePageState(pageInfo.companyId(), false);
    }

    private void changePageState(Long companyId, boolean active) {
        Faq faq = faqRepository.findByCompanyId(companyId).orElseThrow(NullPointerException::new);
        faq.setActive(active);
        faqRepository.save(faq);
    }

}
