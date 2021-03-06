package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ENTITY_GROUP")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(
            mappedBy = "groupOfProduct"
    )
    private List<Product> products;
}