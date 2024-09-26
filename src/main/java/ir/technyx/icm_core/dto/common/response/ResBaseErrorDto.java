package ir.technyx.icm_core.dto.common.response;

import ir.technyx.icm_core.utils.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResBaseErrorDto extends ResBaseDto {

    private String url;

    public static ResBaseErrorDto getInstance() {
        return new ResBaseErrorDto();
    }

    public static ResBaseErrorDto getInstance(String url) {
        ResBaseErrorDto resBaseErrorDto = new ResBaseErrorDto();
        resBaseErrorDto.setUrl(url);
        return resBaseErrorDto;
    }

    public static ResBaseErrorDto getInstance(String url,ErrorMessage errorMessage){
        ResBaseErrorDto resBaseErrorDto = new ResBaseErrorDto();
        resBaseErrorDto.setUrl(url);
        resBaseErrorDto.getErrorMessages().add(errorMessage);
        return resBaseErrorDto;
    }

    public static ResBaseErrorDto getInstance(String url, List<ErrorMessage> errorMessages) {
        ResBaseErrorDto resBaseErrorDto = new ResBaseErrorDto();
        resBaseErrorDto.setUrl(url);
        resBaseErrorDto.setErrorMessages(errorMessages);
        return resBaseErrorDto;
    }

    public static ResBaseErrorDto getInstance(String url, String message) {
        ResBaseErrorDto resBaseErrorDto = new ResBaseErrorDto();
        resBaseErrorDto.setUrl(url);
        resBaseErrorDto.setResponseMessage(message);
        return resBaseErrorDto;
    }


}
