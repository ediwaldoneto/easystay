package br.com.nt.easystay.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDTO {

    private Long id;
    private int number;
    private String roomType;
    private boolean available;
}
