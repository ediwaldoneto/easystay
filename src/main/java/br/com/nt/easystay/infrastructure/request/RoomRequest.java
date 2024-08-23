package br.com.nt.easystay.infrastructure.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RoomRequest {


    private int number;

    @Pattern(regexp = "SINGLE|DOUBLE|SUITE|FAMILY|DELUXE",
            message = "Invalid room type. Allowed values are: SINGLE, DOUBLE, SUITE, FAMILY, DELUXE")
    private String roomType;

    private boolean available;
}
