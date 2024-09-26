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
public class ResMenuAccessLevelItemDto extends ResBaseDto {

    private Long id;

    private Long menuId;

    private String url;

    private boolean read;

    private boolean write;

    private boolean update;

    private boolean delete;

    public ResMenuAccessLevelItemDto(Long id, Long menuId, String url) {
        setId(id);
        setMenuId(menuId);
        setUrl(url);
    }

}
