package br.com.nt.easystay.domain.exception;

import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ReservationNotFound extends EasyStayException {

    private final String detail;

    public ReservationNotFound(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail problemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Reservation not found");
        pb.setDetail(detail);
        return pb;
    }

}
