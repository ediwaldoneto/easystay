package br.com.nt.easystay.domain.exception;

import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BusinessException extends EasyStayException {

    private final String detail;
    private final HttpStatus httpStatus;

    public BusinessException(String detail, HttpStatus httpStatus) {
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    @Override
    public ProblemDetail problemDetail() {
        var pb = ProblemDetail.forStatus(httpStatus);
        pb.setTitle("Business Exception");
        pb.setDetail(detail);
        return pb;
    }
}
