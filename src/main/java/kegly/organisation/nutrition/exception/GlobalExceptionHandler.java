package kegly.organisation.nutrition.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setErrorCode(HttpStatus.NOT_FOUND);
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());
        responseDto.setApiPath(webRequest.getDescription(false));

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
