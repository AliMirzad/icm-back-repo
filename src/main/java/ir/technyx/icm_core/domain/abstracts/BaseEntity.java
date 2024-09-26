package ir.technyx.icm_core.domain.abstracts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;


@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator", parameters = {
            @Parameter(name = "sequence_name", value = "id_sequence"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

  /*  @Version
    @Column(name = "entity_version", nullable = false)
    private Long version;*/

}
