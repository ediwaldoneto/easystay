package br.com.nt.easystay.domain.exception;

import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PaymentNotFound extends EasyStayException {

    private final String detail;

    public PaymentNotFound(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail problemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Payment not found");
        pb.setDetail(detail);
        return pb;
    }
}
