package com.leilao.lance.rapido.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

// Possiveis erros futuros podem ser causados por @Data. Caso ocorra, substituir por @Getter e @Setter
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
	
    @ManyToOne
    protected User user;

    protected String type;
    @CreatedDate
    protected Date creationTime;
    @LastModifiedDate
    protected Date lastUpdateTime;
    protected Double initialBid;
    protected LocalDateTime timeLimit;
    protected boolean active;
    protected String description;
    protected int condicao;
    
    @OneToOne
    protected Bid highestBid;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected Set<ProductImage> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<Bid> bids;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Set<Comment> comments;
    
    public Product(User user, String type, double initialBid) {
    	this.user = user;
    	this.type = type;
    	this.initialBid = initialBid;
    	this.creationTime = null;
    	this.lastUpdateTime =null;
    	this.timeLimit = LocalDateTime.now().plusDays(5);
    	this.active = true;
    	this.highestBid = null;
    	this.images = null;
    	this.bids = new LinkedHashSet<>();
    	this.comments = null;
    }

    public Product() {
        this.user = null;
        this.type = null;
        this.initialBid = null;
        this.creationTime = null;
        this.lastUpdateTime = null;
        this.timeLimit = LocalDateTime.now().plusDays(5);
        this.active = true;
        this.highestBid = null;
        this.images = new LinkedHashSet<>();
        this.bids = new LinkedHashSet<>();
        this.comments = new LinkedHashSet<>();
    }
}
