package ir.technyx.icm_core.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class RegistrationInfo {

    @Column(name = "fk_register_user", nullable = false, updatable = false)
    private Long registerUserId;

    @Column(name = "register_date", nullable = false, updatable = false)
    private LocalDateTime registerDate;

}
