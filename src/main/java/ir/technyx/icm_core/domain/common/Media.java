package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_media", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "fk_file"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_file", nullable = false)
    private File file;

    @Column(name = "active")
    private boolean active;

}
