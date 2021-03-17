package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GROUPS_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "PRODUCT_GROUP",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Set<Product> products  = new HashSet<>();

    public Group(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }
}