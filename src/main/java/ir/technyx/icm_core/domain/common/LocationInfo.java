package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_location_info",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fk_parent", "fk_location_type", "title", "zip_code"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent")
    private LocationInfo parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<LocationInfo> subRegions;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_location_type", nullable = false)
    private ManagementType locationType;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    public LocationInfo(Long id) {
        setId(id);
    }

    public LocationInfo(LocationInfo parent, ManagementType locationType, String title, String zipCode) {
        this(null, parent, locationType, title, zipCode);
    }

    public LocationInfo(Long id, LocationInfo parent, ManagementType locationType, String title, String zipCode) {
        setId(id);
        setParent(parent);
        setLocationType(locationType);
        setTitle(title);
        setZipCode(zipCode);
    }

}
