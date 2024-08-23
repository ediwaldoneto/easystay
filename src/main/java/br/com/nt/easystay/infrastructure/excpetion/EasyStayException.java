package br.com.nt.easystay.infrastructure.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EasyStayException extends RuntimeException {

    public ProblemDetail problemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("EasyStay internal server error");
        return pb;
    }
}
