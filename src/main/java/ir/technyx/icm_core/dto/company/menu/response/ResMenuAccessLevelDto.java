package ir.technyx.icm_core.dto.company.menu.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResMenuAccessLevelDto extends ResBaseDto {

    private Long id;

    private Long userId;

    private Long roleId;

    private String url;

    private boolean read;

    private boolean write;

    private boolean update;

    private boolean delete;

    public ResMenuAccessLevelDto(Long id, Long userId, Long roleId, String url) {
        this(id, userId, roleId, url, false, false, false, false);
    }

}
