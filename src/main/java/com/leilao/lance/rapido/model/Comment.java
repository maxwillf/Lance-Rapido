package com.leilao.lance.rapido.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Comment parent;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parent",fetch = FetchType.EAGER)
    Collection<Comment> children;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Product product;

    @ManyToOne
    User user;

}
