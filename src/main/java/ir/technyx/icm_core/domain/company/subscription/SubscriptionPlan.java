package ir.technyx.icm_core.domain.company.subscription;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_subscription_plan",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "start_date", "end_date", "price"}))
public class SubscriptionPlan extends BaseEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_register_account")
    private User registerAccount;//only admin role

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subscriptionPlan")
    private List<SubscriptionPlanItem> subscriptionPlanItems;

    public SubscriptionPlan(Long id) {
        setId(id);
    }

    public SubscriptionPlan( String title,LocalDateTime startDate,LocalDateTime endDate,Long price,String description){
        this(null,title,startDate,endDate,price,description,null);
    }

    public SubscriptionPlan(String title,LocalDateTime startDate,LocalDateTime endDate,Long price,String description
            ,List<SubscriptionPlanItem> subscriptionPlanItems){
        this(null,title,startDate,endDate,price,description,subscriptionPlanItems);
    }

    public SubscriptionPlan(Long id,String title,LocalDateTime startDate,LocalDateTime endDate,Long price,String description
            ,List<SubscriptionPlanItem> subscriptionPlanItems){
        setId(id);
        setTitle(title);
        setStartDate(startDate);
        setEndDate(endDate);
        setPrice(price);
        setDescription(description);
        setSubscriptionPlanItems(subscriptionPlanItems);
    }

}
