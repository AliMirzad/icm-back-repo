package ir.technyx.icm_core.repository.company.subscription;

import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlanToCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPlanToCompanyRepository extends JpaRepository<SubscriptionPlanToCompany, Long> {
}
