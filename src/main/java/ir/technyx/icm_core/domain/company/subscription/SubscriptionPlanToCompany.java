package ir.technyx.icm_core.domain.company.subscription;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_subscription_plan_to_company",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fk_owner", "fk_subscription_plan"}))
public class SubscriptionPlanToCompany extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_owner")
    private Company owner;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_subscription_plan")
    private SubscriptionPlan subscriptionPlan;

    @Column(name = "factor_id")
    private Long factorId;

    public SubscriptionPlanToCompany(Company owner, SubscriptionPlan subscriptionPlan) {
        setOwner(owner);
        setSubscriptionPlan(subscriptionPlan);
    }

}