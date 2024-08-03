package br.com.nt.easystay.domain.exception;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String message) {
        super(message);
    }
}
