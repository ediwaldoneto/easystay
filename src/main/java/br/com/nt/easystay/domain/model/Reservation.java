package br.com.nt.easystay.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "reservation_number", unique = true, nullable = false)
    private String reservationNumber;
    @Column(name = "check_in")
    private LocalDateTime checkInDate;
    @Column(name = "check_out")
    private LocalDateTime checkOutDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_timing")
    private PaymentTiming paymentTiming;
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;
}
