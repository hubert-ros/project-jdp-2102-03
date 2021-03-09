package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String e_mail;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private final Set<Order> orders = new HashSet<>();
}
