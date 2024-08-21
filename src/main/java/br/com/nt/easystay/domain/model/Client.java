package br.com.nt.easystay.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;


    @PrePersist
    public void prePersist() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }
}
