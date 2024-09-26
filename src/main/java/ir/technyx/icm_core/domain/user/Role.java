package ir.technyx.icm_core.domain.user;


import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_role", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "active"}))
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

    public static final String ROLE_ICM_ADMIN = "ROLE_ICM_ADMIN";
    public static final String ROLE_ICM_USER = "ROLE_ICM_USER";
    public static final String ROLE_CU_ADMIN = "ROLE_CU_ADMIN";
    public static final String ROLE_CU_MEMBER = "ROLE_CU_MEMBER";

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active")
    private boolean active;

    public Role(Long id) {
        setId(id);
    }

    public Role(String name) {
        this(name, true);
    }

    public Role(String name, boolean active) {
        this(null, name, active);
    }

    public Role(Long id, String name, boolean active) {
        setId(id);
        setName(name);
        setActive(active);
    }
}
