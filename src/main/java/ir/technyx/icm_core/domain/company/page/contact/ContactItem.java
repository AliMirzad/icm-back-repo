package ir.technyx.icm_core.domain.company.page.contact;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_contact_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactItem extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_contact_us")
    private ContactUs contactUs;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "response")
    private String response;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_respondent")
    private User userRespondent;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

}
