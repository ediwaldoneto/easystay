package br.com.nt.easystay.domain.exception;

public class ReservationNotFound extends RuntimeException {

    public ReservationNotFound(String message) {
        super(message);
    }
}
