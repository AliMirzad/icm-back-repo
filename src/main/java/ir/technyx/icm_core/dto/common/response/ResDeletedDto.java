package ir.technyx.icm_core.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResDeletedDto extends ResBaseDto {

    private Long id;

    private String code;

    public static ResDeletedDto getInstance() {
        ResDeletedDto resDeletedDto = new ResDeletedDto();
        resDeletedDto.setResponseMessage("Selected Record Is Deleted.");
        return resDeletedDto;
    }

    public static ResDeletedDto getInstance(Long id) {
        ResDeletedDto resDeletedDto = new ResDeletedDto();
        resDeletedDto.setId(id);
        resDeletedDto.setResponseMessage("Selected Record Is Deleted.");
        return resDeletedDto;
    }

    public static ResDeletedDto getInstance(Long id, String message) {
        ResDeletedDto resDeletedDto = new ResDeletedDto();
        resDeletedDto.setId(id);
        resDeletedDto.setResponseMessage(message);
        return resDeletedDto;
    }

    public static ResDeletedDto getInstance(String code) {
        ResDeletedDto resDeletedDto = new ResDeletedDto();
        resDeletedDto.setCode(code);
        resDeletedDto.setResponseMessage("Selected Record Is Deleted.");
        return resDeletedDto;
    }

    public static ResDeletedDto getInstance(String code, String message) {
        ResDeletedDto resDeletedDto = new ResDeletedDto();
        resDeletedDto.setCode(code);
        resDeletedDto.setResponseMessage(message);
        return resDeletedDto;
    }

    public static ResDeletedDto getInstanceByMessage(String message) {
        ResDeletedDto resDeletedDto = new ResDeletedDto();
        resDeletedDto.setResponseMessage(message);
        return resDeletedDto;
    }

}
