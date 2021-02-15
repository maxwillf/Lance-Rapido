package com.leilao.lance.rapido.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdateTime;
    private Double initialBid;

    @OneToMany
    private List<ProductImage> images;

    @OneToMany
    private List<Bid> bids;

    @OneToMany
    private List<Comment> comments;
}
