package br.com.nt.easystay.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email should be valid")
    @Size(max = 255)
    private String email;
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}$", message = "Phone should be valid")
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
}
