package br.com.nt.easystay.infrastructure.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RoomRequest {

    @Min(value = 1, message = "Room number must be greater than 0")
    private int number;
    @NotBlank(message = "Room type must not be empty")
    @Pattern(regexp = "SINGLE|DOUBLE|SUITE|FAMILY|DELUXE",
            message = "Invalid room type. Allowed values are: SINGLE, DOUBLE, SUITE, FAMILY, DELUXE")
    private String roomType;
    @NotNull(message = "Availability must be specified")
    private boolean available;
}
