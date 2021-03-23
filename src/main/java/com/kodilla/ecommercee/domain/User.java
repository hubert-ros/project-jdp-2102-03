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
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @NonNull
    private String userName;

    @NotNull
    @NonNull
    private String eMail;

    @NotNull
    @NonNull
    private String address;

    private boolean blocked = false;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public User(@NotNull @NonNull String userName, @NotNull @NonNull String eMail, @NotNull @NonNull String address, boolean blocked, Set<Order> orders) {
        this.userName = userName;
        this.eMail = eMail;
        this.address = address;
        this.blocked = blocked;
        this.orders = orders;
    }
}
