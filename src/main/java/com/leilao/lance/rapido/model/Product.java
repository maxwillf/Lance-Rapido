package com.leilao.lance.rapido.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
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
    private LocalDateTime timeLimit;
    private boolean active;
    
    @OneToOne
    private Bid highestBid;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ProductImage> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Bid> bids;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments;
    
    public Product(User user, String name, double initialBid) {
    	this.user = user;
    	this.name = name;
    	this.initialBid = initialBid;
    	this.creationTime = LocalDateTime.now();
    	this.lastUpdateTime = LocalDateTime.now();
    	this.timeLimit = LocalDateTime.now().plusDays(5);
    	this.active = true;
    	this.highestBid = null;
    	this.images = null;
    	this.bids = null;
    	this.comments = null;
    }
    
}
