package br.com.nt.easystay.domain.model;

public enum ReservationStatus {

    PENDING,
    CONFIRMED,
    CHECKED_IN,
    CHECKED_OUT,
    CANCELLED;

    public static ReservationStatus fromString(String reservation) {
        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.name().equalsIgnoreCase(reservation)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + reservation);
    }

}
