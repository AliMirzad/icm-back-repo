package ir.technyx.icm_core.service.company.page;

public interface PageManagement<P extends PageInfo> {

    boolean support(P pageInfo);

    void addPage(P pageInfo);

    void activePage(P pageInfo);

    void deactivatePage(P pageInfo);


}
