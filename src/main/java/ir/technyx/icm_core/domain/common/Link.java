package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_link", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_link_type", "url"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_link_type", nullable = false)
    private ManagementType linkType;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

}
