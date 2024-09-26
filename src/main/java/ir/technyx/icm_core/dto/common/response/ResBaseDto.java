package ir.technyx.icm_core.dto.common.response;

import ir.technyx.icm_core.utils.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResBaseDto implements Serializable {

    private LocalDateTime responseTime;

    private String responseMessage;

    private List<ErrorMessage> errorMessages;

    public LocalDateTime getResponseTime() {
        return LocalDateTime.now();
    }

    public List<ErrorMessage> getErrorMessages() {
        if (errorMessages == null) {
            errorMessages = new ArrayList<>();
        }
        return errorMessages;
    }

}
