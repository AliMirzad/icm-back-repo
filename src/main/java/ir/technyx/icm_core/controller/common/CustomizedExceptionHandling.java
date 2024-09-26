package ir.technyx.icm_core.controller.common;

import io.jsonwebtoken.ExpiredJwtException;
import ir.technyx.icm_core.dto.common.response.ResBaseErrorDto;
import ir.technyx.icm_core.utils.ErrorMessage;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;
import java.util.List;

@RestControllerAdvice
@AllArgsConstructor
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    private final MessageUtil messageUtil;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        List<ErrorMessage> errorMessages = exception.getBindingResult().getAllErrors().stream().map(error ->
                new ErrorMessage(((FieldError) error).getField(),
                                 messageUtil.getLocalizedMessage(error.getDefaultMessage()),
                                 error.toString()
                                )
        ).toList();

        Object convertToObject = createResExceptionDto(request, errorMessages);
        return ResponseEntity.badRequest().body(convertToObject);

    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResBaseErrorDto> handleBadCredentialsException(Exception exception, WebRequest request) {
       return ResponseEntityHelper.unAuthorized(
               createResExceptionDto(
                        request,
                        new ErrorMessage("BadCredentialsException","security.badCredentialsException",
                                                   exception.toString())));
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ResBaseErrorDto> handleAccountStatusException(Exception exception, WebRequest request) {
        return ResponseEntityHelper.forbidden(
                createResExceptionDto(
                        request,
                        new ErrorMessage("AccountStatusException","security.accountStatusException",
                                                   exception.toString())));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResBaseErrorDto> handleAccessDeniedException(Exception exception, WebRequest request) {
        return ResponseEntityHelper.forbidden(
                createResExceptionDto(
                        request,
                        new ErrorMessage("AccessDeniedException","security.accessDeniedException",
                                                    exception.toString())));
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResBaseErrorDto> handleSignatureException(Exception exception, WebRequest request) {
        return ResponseEntityHelper.forbidden(
               createResExceptionDto(
                        request,
                        new ErrorMessage("SignatureException","security.signatureException",
                                                   exception.toString())));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResBaseErrorDto> handleExpiredJwtException(Exception exception, WebRequest request) {
       return ResponseEntityHelper.forbidden(
                createResExceptionDto(
                        request,
                        new ErrorMessage("ExpiredJwtException","security.expiredJwtException",
                                                   exception.toString())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResBaseErrorDto> handleGeneralException(Exception exception, WebRequest request) {
        return ResponseEntityHelper.internalServerError(
                createResExceptionDto(
                        request,
                        new ErrorMessage("InternalServerException","generalException.internalServerException",
                                                   exception.toString())));
    }


    private ResBaseErrorDto createResExceptionDto(WebRequest request, ErrorMessage errorMessage) {
        errorMessage.setError(messageUtil.getLocalizedMessage(errorMessage.getError()));
        String requestUrl = ((ServletWebRequest) request).getRequest()
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        return ResBaseErrorDto.getInstance(requestUrl, errorMessage);
    }

    private ResBaseErrorDto createResExceptionDto(WebRequest request, List<ErrorMessage> errorMessages) {
        String requestUrl = ((ServletWebRequest) request).getRequest()
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        return ResBaseErrorDto.getInstance(requestUrl, errorMessages);
    }

}
