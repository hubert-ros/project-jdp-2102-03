package com.kodilla.ecommercee.domain;

import lombok.*;
import org.springframework.context.annotation.Lazy;

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

    @NotNull
    private String name;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "PRODUCT_GROUP",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Set<Product> products  = new HashSet<>();

    public Group(@NotNull String name, @Lazy Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Group(String name) {
        this.name = name;
    }
}