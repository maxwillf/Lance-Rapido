package com.leilao.lance.rapido.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @ToString.Exclude
    Comment parent;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parent",fetch = FetchType.EAGER)
    Collection<Comment> children;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

//    @ManyToOne
//    User user;

    String content;

}
