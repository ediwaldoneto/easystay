package br.com.nt.easystay.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Room")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private int number;
    @Column(name = "roomType")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Column(name = "available")
    private boolean available;

}
