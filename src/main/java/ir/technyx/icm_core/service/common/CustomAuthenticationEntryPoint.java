package ir.technyx.icm_core.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ir.technyx.icm_core.dto.common.response.ResBaseErrorDto;
import ir.technyx.icm_core.utils.ErrorMessage;
import ir.technyx.icm_core.utils.MessageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final MessageUtil messageUtil;

    public CustomAuthenticationEntryPoint(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ResBaseErrorDto resBaseErrorDto = new ResBaseErrorDto();
        List<ErrorMessage> errorMessages = new ArrayList<>();
        errorMessages.add(new ErrorMessage("UN_AUTHORIZEDException",
                                         messageUtil.getLocalizedMessage("security.un_authorized_exception"),
                                         authException.toString())
                         );


        resBaseErrorDto.setErrorMessages(errorMessages);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(responseStream, resBaseErrorDto);
        responseStream.flush();
    }
}
