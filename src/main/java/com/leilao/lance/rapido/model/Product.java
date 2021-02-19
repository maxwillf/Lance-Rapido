package com.leilao.lance.rapido.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    private String name;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdateTime;
    private Double initialBid;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ProductImage> images;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Bid> bids;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> comments;
}
