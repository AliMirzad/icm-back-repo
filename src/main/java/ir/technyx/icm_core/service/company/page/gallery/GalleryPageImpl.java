package ir.technyx.icm_core.service.company.page.gallery;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.page.Gallery;
import ir.technyx.icm_core.repository.company.page.GalleryRepository;
import ir.technyx.icm_core.service.company.page.PageInfo;
import ir.technyx.icm_core.service.company.page.PageManagement;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GalleryPageImpl implements PageManagement<PageInfo> {

    private final GalleryRepository galleryRepository;

    @Override
    public boolean support(PageInfo pageInfo) {
        return pageInfo.pageSubType().equals(Gallery.GALLERY_PAGE_TYPE);
    }

    @Override
    @Transactional
    public void addPage(PageInfo pageInfo) {
        Optional<Gallery> galleryOptional = galleryRepository.findByCompanyId(pageInfo.companyId());
        if (galleryOptional.isEmpty()) {
            Gallery gallery = new Gallery(new Company(pageInfo.companyId()), true, UserUtility.getCurrentRegistrationInfo());
            galleryRepository.save(gallery);
        }
    }

    @Override
    public void activePage(PageInfo pageInfo) {
        changePageState(pageInfo.companyId(), true);
    }

    @Override
    public void deactivatePage(PageInfo pageInfo) {
        changePageState(pageInfo.companyId(), false);
    }

    private void changePageState(Long companyId, boolean active) {
        Gallery gallery = galleryRepository.findByCompanyId(companyId).orElseThrow(NullPointerException::new);
        gallery.setActive(active);
        galleryRepository.save(gallery);
    }

}
