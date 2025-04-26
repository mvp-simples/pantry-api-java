package io.com.github.pantry.api.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String userName;

    private String password;

    private boolean active = true;

    private String role; // USER, ADMIN, etc

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user;
}
