package br.com.nt.easystay.infrastructure.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponse {

    private Long id;
    private int number;
    private String roomType;
    private boolean available;
}
