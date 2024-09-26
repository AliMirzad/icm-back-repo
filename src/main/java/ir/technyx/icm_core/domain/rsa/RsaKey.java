package ir.technyx.icm_core.domain.rsa;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_rsa_key", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_company", "active", "version"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RsaKey extends BaseEntity {

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "private_key")
    private String privateKey;

    //    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_company")
    private Long company;

    @Column(name = "active")
    private boolean active;

    @Column(name = "rsk_version")
    private Integer rskVersion;

    @Embedded
    private RegistrationInfo registrationInfo;

}
