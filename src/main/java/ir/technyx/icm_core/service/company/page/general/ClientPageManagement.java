package ir.technyx.icm_core.service.company.page.general;

import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.company.page.content.Page;
import ir.technyx.icm_core.mappers.company.CompanyMapper;
import ir.technyx.icm_core.repository.common.ManagementTypeRepository;
import ir.technyx.icm_core.repository.company.page.PageRepository;
import ir.technyx.icm_core.service.company.page.PageManagement;
import ir.technyx.icm_core.service.company.page.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientPageManagement implements PageManagement<PageInfo> {

    private final ManagementTypeRepository managementTypeRepository;
    private final PageRepository pageRepository;

    @Override
    public boolean support(PageInfo pageInfo) {
        return managementTypeRepository.findAllByCode(ManagementType.CLIENT_PAGE_TYPE).orElseThrow(NullPointerException::new)
                .stream().anyMatch(mtp->mtp.getSubType().equals(pageInfo.pageSubType()));
    }

    @Override
    public void addPage(PageInfo pageInfo) {
        Optional<Page> currentPage = pageRepository
                .findByCompanyIdAndPageSubType(pageInfo.companyId(), pageInfo.pageSubType());
        ManagementType currentPageType = managementTypeRepository
                .findByCodeAndSubType(ManagementType.CLIENT_PAGE_TYPE, pageInfo.pageSubType())
                .orElseThrow(NullPointerException::new);
        if(currentPage.isEmpty()){

            Page page = new Page(
                    CompanyMapper.toCompany(pageInfo.companyId()),
                    currentPageType,
                    pageInfo.pageSubType(),
                    null,
                    null,
                    null,
                    true,
                    null
            );
            pageRepository.save(page);
        }
    }

    @Override
    public void activePage(PageInfo pageInfo) {
        changePageState(pageInfo,true);
    }

    @Override
    public void deactivatePage(PageInfo pageInfo) {
        changePageState(pageInfo,false);
    }

    private void changePageState(PageInfo pageInfo, boolean active) {
        Page currentPage = pageRepository
                .findByCompanyIdAndPageSubType(pageInfo.companyId(), pageInfo.pageSubType())
                .orElseThrow(NullPointerException::new);
        currentPage.setActive(active);
        pageRepository.save(currentPage);
    }
}
