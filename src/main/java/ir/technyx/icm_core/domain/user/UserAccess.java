package ir.technyx.icm_core.domain.user;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.company.menu.MenuAccessLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_user_access", uniqueConstraints = {@UniqueConstraint(columnNames = {"fk_user", "fk_role"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccess extends BaseEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_role", nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userAccess")
    private List<MenuAccessLevel> menuAccessLevelList;

    public UserAccess(Long userId) {
        setUser(new User(userId));
    }

    public UserAccess(User user, Role role) {
        setUser(user);
        setRole(role);
    }

}
