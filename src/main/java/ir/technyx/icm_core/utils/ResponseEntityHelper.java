package ir.technyx.icm_core.utils;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface ResponseEntityHelper {

    static ResponseEntity<?> ok() {
        return createResponseEntity(HttpStatus.OK);
    }

    static <R extends ResBaseDto> ResponseEntity<R> ok(R entity) {
        return createResponseEntity(entity, HttpStatus.OK);
    }

    static <R extends ResBaseDto> ResponseEntity<R> ok(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.OK, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> ok(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.OK, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> ok(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.OK, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> ok(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.OK);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> ok(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.OK, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> ok(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.OK, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> ok(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.OK, responseMessage, errorMessages);
    }

    static ResponseEntity<?> created() {
        return createResponseEntity(HttpStatus.CREATED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> created(R entity) {
        return createResponseEntity(entity, HttpStatus.CREATED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> created(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.CREATED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> created(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.CREATED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> created(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.CREATED, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> created(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.CREATED);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> created(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.CREATED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> created(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.CREATED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> created(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.CREATED, responseMessage, errorMessages);
    }

    static ResponseEntity<?> accepted() {
        return createResponseEntity(HttpStatus.ACCEPTED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> accepted(R entity) {
        return createResponseEntity(entity, HttpStatus.ACCEPTED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> accepted(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.ACCEPTED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> accepted(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.ACCEPTED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> accepted(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.ACCEPTED, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> accepted(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.ACCEPTED);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> accepted(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.ACCEPTED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> accepted(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.ACCEPTED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> accepted(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.ACCEPTED, responseMessage, errorMessages);
    }

    static ResponseEntity<?> notModified() {
        return createResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notModified(R entity) {
        return createResponseEntity(entity, HttpStatus.NOT_MODIFIED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notModified(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.NOT_MODIFIED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notModified(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.NOT_MODIFIED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notModified(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.NOT_MODIFIED, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notModified(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.NOT_MODIFIED);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notModified(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.NOT_MODIFIED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notModified(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.NOT_MODIFIED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notModified(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.NOT_MODIFIED, responseMessage, errorMessages);
    }

    static ResponseEntity<?> badRequest() {
        return createResponseEntity(HttpStatus.BAD_REQUEST);
    }

    static <R extends ResBaseDto> ResponseEntity<R> badRequest(R entity) {
        return createResponseEntity(entity, HttpStatus.BAD_REQUEST);
    }

    static <R extends ResBaseDto> ResponseEntity<R> badRequest(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.BAD_REQUEST, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> badRequest(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.BAD_REQUEST, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> badRequest(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.BAD_REQUEST, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> badRequest(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.BAD_REQUEST);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> badRequest(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.BAD_REQUEST, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> badRequest(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.BAD_REQUEST, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> badRequest(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.BAD_REQUEST, responseMessage, errorMessages);
    }

    static ResponseEntity<?> internalServerError() {
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static <R extends ResBaseDto> ResponseEntity<R> internalServerError(R entity) {
        return createResponseEntity(entity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static <R extends ResBaseDto> ResponseEntity<R> internalServerError(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.INTERNAL_SERVER_ERROR, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> internalServerError(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.INTERNAL_SERVER_ERROR, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> internalServerError(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.INTERNAL_SERVER_ERROR, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> internalServerError(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> internalServerError(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.INTERNAL_SERVER_ERROR, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> internalServerError(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.INTERNAL_SERVER_ERROR, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> internalServerError(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.INTERNAL_SERVER_ERROR, responseMessage, errorMessages);
    }

    static ResponseEntity<?> notFound() {
        return createResponseEntity(HttpStatus.NOT_FOUND);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notFound(R entity) {
        return createResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notFound(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.NOT_FOUND, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notFound(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.NOT_FOUND, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> notFound(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.NOT_FOUND, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notFound(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.NOT_FOUND);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notFound(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.NOT_FOUND, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notFound(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.NOT_FOUND, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> notFound(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.NOT_FOUND, responseMessage, errorMessages);
    }

    static ResponseEntity<?> unAuthorized() {
        return createResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unAuthorized(R entity) {
        return createResponseEntity(entity, HttpStatus.UNAUTHORIZED);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unAuthorized(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.UNAUTHORIZED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unAuthorized(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.UNAUTHORIZED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unAuthorized(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.UNAUTHORIZED, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unAuthorized(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.UNAUTHORIZED);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unAuthorized(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.UNAUTHORIZED, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unAuthorized(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.UNAUTHORIZED, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unAuthorized(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.UNAUTHORIZED, responseMessage, errorMessages);
    }

    static ResponseEntity<?> forbidden() {
        return createResponseEntity(HttpStatus.FORBIDDEN);
    }

    static <R extends ResBaseDto> ResponseEntity<R> forbidden(R entity) {
        return createResponseEntity(entity, HttpStatus.FORBIDDEN);
    }

    static <R extends ResBaseDto> ResponseEntity<R> forbidden(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.FORBIDDEN, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> forbidden(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.FORBIDDEN, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> forbidden(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.FORBIDDEN, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> forbidden(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.FORBIDDEN);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> forbidden(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.FORBIDDEN, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> forbidden(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.FORBIDDEN, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> forbidden(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.FORBIDDEN, responseMessage, errorMessages);
    }

    static ResponseEntity<?> processing() {
        return createResponseEntity(HttpStatus.PROCESSING);
    }

    static <R extends ResBaseDto> ResponseEntity<R> processing(R entity) {
        return createResponseEntity(entity, HttpStatus.PROCESSING);
    }

    static <R extends ResBaseDto> ResponseEntity<R> processing(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.PROCESSING, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> processing(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.PROCESSING, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> processing(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.PROCESSING, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> processing(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.PROCESSING);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> processing(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.PROCESSING, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> processing(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.PROCESSING, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> processing(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.PROCESSING, responseMessage, errorMessages);
    }

    static ResponseEntity<?> unsupportedMediaType() {
        return createResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unsupportedMediaType(R entity) {
        return createResponseEntity(entity, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unsupportedMediaType(R entity, String responseMessage) {
        return createResponseEntity(entity, HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unsupportedMediaType(R entity, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<R> unsupportedMediaType(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entity, HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseMessage, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unsupportedMediaType(List<R> entities) {
        return createResponseEntity(entities, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unsupportedMediaType(List<R> entities, String responseMessage) {
        return createResponseEntity(entities, HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseMessage);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unsupportedMediaType(List<R> entities, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorMessages);
    }

    static <R extends ResBaseDto> ResponseEntity<List<R>> unsupportedMediaType(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return createResponseEntity(entities, HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseMessage, errorMessages);
    }

    private static ResponseEntity<?> createResponseEntity(HttpStatus httpStatus) {
        return new ResponseEntity<>(null, null, httpStatus);
    }

    private static <R extends ResBaseDto> ResponseEntity<R> createResponseEntity(R entity, HttpStatus httpStatus) {
        return new ResponseEntity<>(entity, null, httpStatus);
    }

    private static <R extends ResBaseDto> ResponseEntity<R> createResponseEntity(R entity, HttpStatus httpStatus, String responseMessage) {
        return new ResponseEntity<>(addResponseMessage(entity, responseMessage), null, httpStatus);
    }


    private static <R extends ResBaseDto> ResponseEntity<R> createResponseEntity(R entity, HttpStatus httpStatus, List<ErrorMessage> errorMessages) {
        return new ResponseEntity<>(addErrorMessages(entity, errorMessages), null, httpStatus);
    }

    private static <R extends ResBaseDto> ResponseEntity<R> createResponseEntity(R entity, HttpStatus httpStatus, String responseMessage, List<ErrorMessage> errorMessages) {
        return new ResponseEntity<>(addMessages(entity, responseMessage, errorMessages), null, httpStatus);
    }

    private static <R extends ResBaseDto> ResponseEntity<List<R>> createResponseEntity(List<R> entities, HttpStatus httpStatus) {
        return new ResponseEntity<>(entities, null, httpStatus);
    }

    private static <R extends ResBaseDto> ResponseEntity<List<R>> createResponseEntity(List<R> entities, HttpStatus httpStatus, String responseMessage) {
        return new ResponseEntity<>(addResponseMessage(entities, responseMessage), null, httpStatus);
    }


    private static <R extends ResBaseDto> ResponseEntity<List<R>> createResponseEntity(List<R> entities, HttpStatus httpStatus, List<ErrorMessage> errorMessages) {
        return new ResponseEntity<>(addErrorMessages(entities, errorMessages), null, httpStatus);
    }

    private static <R extends ResBaseDto> ResponseEntity<List<R>> createResponseEntity(List<R> entities, HttpStatus httpStatus, String responseMessage, List<ErrorMessage> errorMessages) {
        return new ResponseEntity<>(addMessages(entities, responseMessage, errorMessages), null, httpStatus);
    }

    private static <R extends ResBaseDto> List<R> addResponseMessage(List<R> entities, String responseMessage) {
        return entities.stream().map(rm -> addResponseMessage(rm, responseMessage)).collect(Collectors.toList());
    }

    private static <R extends ResBaseDto> R addResponseMessage(R entity, String responseMessage) {
        entity.setResponseMessage(responseMessage);
        return entity;
    }

    private static <R extends ResBaseDto> List<R> addErrorMessages(List<R> entities, List<ErrorMessage> errorMessages) {
        return entities.stream().map(rm -> addErrorMessages(rm, errorMessages)).collect(Collectors.toList());
    }

    private static <R extends ResBaseDto> R addErrorMessages(R entity, List<ErrorMessage> errorMessages) {
        entity.setErrorMessages(errorMessages);
        return entity;
    }

    private static <R extends ResBaseDto> List<R> addMessages(List<R> entities, String responseMessage, List<ErrorMessage> errorMessages) {
        return entities.stream().map(rm -> addMessages(rm, responseMessage, errorMessages)).collect(Collectors.toList());
    }

    private static <R extends ResBaseDto> R addMessages(R entity, String responseMessage, List<ErrorMessage> errorMessages) {
        entity.setResponseMessage(responseMessage);
        entity.setErrorMessages(errorMessages);
        return entity;
    }

}
