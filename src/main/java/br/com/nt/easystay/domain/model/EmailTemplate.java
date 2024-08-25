package br.com.nt.easystay.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "email_templates")
@Getter
@Setter
@NoArgsConstructor
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "subject", nullable = false)
    private String subject;
    @Lob
    @Column(name = "body", nullable = false)
    private String body;
    @ManyToOne
    @JoinColumn(name = "logo_id")
    private Logo logo;
    private String description;
}
