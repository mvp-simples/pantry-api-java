package io.com.github.pantry.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Builder
public class User {

    @Id
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String address;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
}