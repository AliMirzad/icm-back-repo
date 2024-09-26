package ir.technyx.icm_core.repository.company.subscription;

import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    Optional<SubscriptionPlan> findByCode(@Param("code") String code);

    void deleteByCode(@Param("code") String code);

}
