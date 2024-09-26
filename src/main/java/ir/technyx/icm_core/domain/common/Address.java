package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_address", uniqueConstraints = @UniqueConstraint(columnNames = {"fk_location_info", "exact_location", "postal_code"}))
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_location_info", nullable = false)
    private LocationInfo locationInfo;

    @Column(name = "exact_location", nullable = false)
    private String exactLocation;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    public Address(LocationInfo locationInfo, String exactLocation, String postalCode) {
        this(null, locationInfo, exactLocation, postalCode);
    }

    public Address(Long id, LocationInfo locationInfo, String exactLocation, String postalCode) {
        setId(id);
        setLocationInfo(locationInfo);
        setExactLocation(exactLocation);
        setPostalCode(postalCode);
    }

}
