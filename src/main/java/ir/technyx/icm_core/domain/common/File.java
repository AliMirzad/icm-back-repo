package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_file", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_file_type", "url"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_file_type", nullable = false)
    private ManagementType fileType;

    @Column(name = "url", nullable = false)
    private String url;

}
