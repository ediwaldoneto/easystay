package br.com.nt.easystay.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Payment")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "cardExpiry")
    private String cardExpiry;
    @Column(name = "cardCvc")
    private String cardCvc;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
