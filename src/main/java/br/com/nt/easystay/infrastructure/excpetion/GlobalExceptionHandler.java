package br.com.nt.easystay.infrastructure.excpetion;

import br.com.nt.easystay.domain.exception.BusinessException;
import br.com.nt.easystay.domain.exception.ReservationNotFound;
import br.com.nt.easystay.domain.exception.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EasyStayException.class)
    public ProblemDetail handleEasyStayException(EasyStayException e) {
        return e.problemDetail();
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ProblemDetail handleRoomNotFoundException(RoomNotFoundException e) {
        return e.problemDetail();
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException e) {
        return e.problemDetail();
    }

    @ExceptionHandler(ReservationNotFound.class)
    public ProblemDetail handleReservationNotFound(ReservationNotFound e) {
        return e.problemDetail();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ProblemDetail handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getLocalizedMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation error in fields.");
        problemDetail.setTitle("Validation Error");
        problemDetail.setInstance(URI.create(request.getDescription(false)));
        problemDetail.setProperty("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ProblemDetail> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.PAYLOAD_TOO_LARGE, "File size exceeds allowed limit");
        problemDetail.setTitle("Upload Error");
        problemDetail.setInstance(URI.create(request.getDescription(false)));

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(problemDetail);
    }
}
