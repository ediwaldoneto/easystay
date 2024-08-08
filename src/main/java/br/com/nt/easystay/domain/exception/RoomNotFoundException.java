package br.com.nt.easystay.domain.exception;

import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class RoomNotFoundException extends EasyStayException {

    private final String detail;

    public RoomNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail problemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Room not found");
        pb.setDetail(detail);
        return pb;
    }
}
