package br.com.nt.easystay.domain.model;

public enum PaymentTiming {

    AT_RESERVATION,   // Pagamento no momento da reserva
    AT_CHECKOUT;       // Pagamento no momento do checkout

    public static PaymentTiming fromString(String paymentTiming) {
        for (PaymentTiming timing : PaymentTiming.values()) {
            if (timing.name().equalsIgnoreCase(paymentTiming)) {
                return timing;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + paymentTiming);
    }
}

