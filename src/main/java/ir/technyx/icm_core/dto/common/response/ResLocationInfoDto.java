package ir.technyx.icm_core.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResLocationInfoDto extends ResBaseDto {

    private Long id;

    private String parentTitle;

    private String parentTypeTitle;

    private String typeTitle;

    private String title;

    private String zipCode;

    public ResLocationInfoDto(Long id) {
        setId(id);
    }

    public ResLocationInfoDto(Long id, String typeTitle, String title, String zipCode) {
        setId(id);
        setTypeTitle(typeTitle);
        setTitle(title);
        setZipCode(zipCode);
    }

}