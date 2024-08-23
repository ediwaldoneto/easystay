package br.com.nt.easystay.domain.exception;

import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DuplicatePaymentException extends EasyStayException {

    private final String detail;

    public DuplicatePaymentException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail problemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("Duplicate payment");
        pb.setDetail(detail);
        return pb;
    }
}
