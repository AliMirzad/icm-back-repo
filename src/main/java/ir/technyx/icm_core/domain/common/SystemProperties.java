package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_system_properties", uniqueConstraints = @UniqueConstraint(columnNames = {"item_Key", "item_value"}))
@Getter
@Setter
@NoArgsConstructor
public class SystemProperties extends BaseEntity {

    public static final String SUPPORT_PAGE = "support_page";

    @Column(name = "item_Key", nullable = false)
    private String key;

    @Column(name = "item_value", nullable = false)
    private String value;

    @Column(name = "item_active")
    private boolean active;

    @Embedded
    private RegistrationInfo registrationInfo;

    public SystemProperties(String key, String value, RegistrationInfo registrationInfo) {
        this(key, value, true, registrationInfo);
    }

    public SystemProperties(String key, String value, boolean active, RegistrationInfo registrationInfo) {
        this(null, key, value, active, registrationInfo);
    }

    public SystemProperties(Long id, String key, String value, boolean active, RegistrationInfo registrationInfo) {
        setId(id);
        setKey(key);
        setValue(value);
        setActive(active);
        setRegistrationInfo(registrationInfo);
    }
}
