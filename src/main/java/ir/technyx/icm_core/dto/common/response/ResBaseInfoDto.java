package ir.technyx.icm_core.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResBaseInfoDto extends ResBaseDto {

    private Long id;

    public static ResBaseInfoDto getInstance() {
        ResBaseInfoDto resBaseInfoDto = new ResBaseInfoDto();
        resBaseInfoDto.setResponseMessage("Request is complete");
        return resBaseInfoDto;
    }

    public static ResBaseInfoDto getInstance(Long id) {
        ResBaseInfoDto resBaseInfoDto = new ResBaseInfoDto();
        resBaseInfoDto.setId(id);
        resBaseInfoDto.setResponseMessage("Request is complete");
        return resBaseInfoDto;
    }

    public static ResBaseInfoDto getInstance(String responseMessage) {
        ResBaseInfoDto resBaseInfoDto = new ResBaseInfoDto();
        resBaseInfoDto.setResponseMessage(responseMessage);
        return resBaseInfoDto;
    }

    public static ResBaseInfoDto getInstance(Long id, String responseMessage) {
        ResBaseInfoDto resBaseInfoDto = new ResBaseInfoDto();
        resBaseInfoDto.setId(id);
        resBaseInfoDto.setResponseMessage(responseMessage);
        return resBaseInfoDto;
    }

}
