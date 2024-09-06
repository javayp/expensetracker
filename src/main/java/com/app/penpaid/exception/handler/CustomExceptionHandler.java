package com.app.penpaid.exception.handler;

import com.app.penpaid.exception.model.ApiError;
import com.app.penpaid.exception.parent.PenPaidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.app.penpaid.constants.PenPaidConstants.*;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleAllBadRequest(final Exception exception, final HttpServletRequest request, final WebRequest webRequest) {
        return handleExceptionInternal(exception,
                buildErrorMessage(request.getRequestURI(),exception),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }

    private ApiError buildErrorMessage(String path, Exception exception) {
        String errorCode = (exception instanceof PenPaidException) ? extractCustomExceptionCode((PenPaidException) exception) : "UNKNOWN_ERROR_CODE";
        String errorDescription = exception.getLocalizedMessage();
        log.error("Exception stackTrace",exception);
        return new ApiError(path,errorCode,errorDescription);
    }


    private String extractCustomExceptionCode(PenPaidException penpaidException){
        return penpaidException.getCode();
    }



}
