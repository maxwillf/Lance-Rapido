package com.leilao.lance.rapido.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

// Possiveis erros futuros podem ser causados por @Data. Caso ocorra, substituir por @Getter e @Setter
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
	
    @ManyToOne
    protected User user;

    protected String type;
    protected LocalDateTime creationTime;
    protected LocalDateTime lastUpdateTime;
    protected Double initialBid;
    protected LocalDateTime timeLimit;
    protected boolean active;
    protected String description;
    protected int condicao;
    
    @OneToOne
    protected Bid highestBid;

    @OneToMany(fetch = FetchType.EAGER)
    protected Set<ProductImage> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<Bid> bids;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<Comment> comments;
    
    public Product(User user, String type, double initialBid) {
    	this.user = user;
    	this.type = type;
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
