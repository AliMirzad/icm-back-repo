package ir.technyx.icm_core.domain.company.subscription;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.ManagementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_subscription_plan_item",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fk_subscription_plan", "fk_page_type"}))
public class SubscriptionPlanItem extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_subscription_plan")
    private SubscriptionPlan subscriptionPlan;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_page_type")
    private ManagementType pageType;

    @Column(name = "total_media_size")
    private Integer totalMediaSize;

    @Column(name = "total_item_size")
    private Integer totalItemSize;

    public SubscriptionPlanItem(ManagementType pageType,Integer totalMediaSize, Integer totalItemSize){
        setPageType(pageType);
        setTotalMediaSize(totalMediaSize);
        setTotalItemSize(totalItemSize);
    }

}
