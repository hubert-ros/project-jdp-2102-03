package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String userKey;

    @NotNull
    @NonNull
    private String userName;

    @NotNull
    @NonNull
    private String eMail;

    @NotNull
    @NonNull
    private String address;

    @NotNull
    private Boolean isBlocked = false;

    @OneToMany(mappedBy = "user",

            fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();
  
}
