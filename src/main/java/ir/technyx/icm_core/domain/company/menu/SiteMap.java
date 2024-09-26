package ir.technyx.icm_core.domain.company.menu;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_site_map", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company", "active", "file_version"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteMap extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company")
    private Company company;

    @Column(name = "active")
    private boolean active;

    @Column(name = "file_version")
    private LocalDateTime fileVersion;

    @Column(name = "file_path")
    private String file_path;

}
