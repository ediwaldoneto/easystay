package br.com.nt.easystay.infrastructure.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class ReservationRequest {

    @NotNull(message = "Check-in date is mandatory")
    @Future(message = "Check-in date must be in the future")
    private LocalDateTime checkInDate;

    @NotNull(message = "Check-out date is mandatory")
    @Future(message = "Check-out date must be in the future")
    private LocalDateTime checkOutDate;

    private ClientRequest client;

    @NotNull(message = "Room ID is mandatory")
    @Positive(message = "Room ID must be a positive number")
    private Long roomId;

    @NotBlank(message = "Payment timing must not be empty")
    @Pattern(regexp = "AT_RESERVATION|AT_CHECKOUT",
            message = "Invalid payment timing. Allowed values are: AT_RESERVATION, AT_CHECKOUT")
    private String paymentTiming;
    @Valid
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentRequest payment;


}
