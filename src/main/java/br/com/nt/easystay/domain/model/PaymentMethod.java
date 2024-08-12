package br.com.nt.easystay.domain.model;


public enum PaymentMethod {

    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    BANK_TRANSFER;

    public static PaymentMethod fromString(String paymentMethod) {
        for (PaymentMethod timing : PaymentMethod.values()) {
            if (timing.name().equalsIgnoreCase(paymentMethod)) {
                return timing;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + paymentMethod);
    }
}
