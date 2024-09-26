package ir.technyx.icm_core.domain.company.menu;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.user.UserAccess;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "tb_menu_access_level", uniqueConstraints = {@UniqueConstraint(columnNames = {"fk_user_access", "fk_menu"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuAccessLevel extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_access", nullable = false)
    private UserAccess userAccess;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_menu", nullable = false)
    private Menu menu;

    @Column(name = "access_read")
    private boolean read;

    @Column(name = "access_write")
    private boolean write;

    @Column(name = "access_update")
    private boolean update;

    @Column(name = "access_delete")
    private boolean delete;

    public MenuAccessLevel(Menu menu, boolean allAccessType) {
        this(null, menu, allAccessType);
    }

    public MenuAccessLevel(Long id, Menu menu, boolean allAccessType) {
        setId(id);
        setMenu(menu);
        setWrite(allAccessType);
        setRead(allAccessType);
        setUpdate(allAccessType);
        setDelete(allAccessType);
    }

    public List<String> getPermissions() {
        return Stream.of(
                        this.isRead() ? "read" : null,
                        this.isWrite() ? "write" : null,
                        this.isUpdate() ? "update" : null,
                        this.isDelete() ? "delete" : null
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
