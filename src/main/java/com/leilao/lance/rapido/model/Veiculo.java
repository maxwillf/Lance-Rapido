package com.leilao.lance.rapido.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@Data
public class Veiculo extends Product {

	private static final long serialVersionUID = 1L;
    
	private String modelo;
	private String combustivel;
	private String tipoPneu;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ProductImage documento;

	public Veiculo(){
			super();
	}
    public Veiculo(User user, String type, double initialBid, String modelo, String combustivel, String tipoPneu, ProductImage documento) {
    	super(user, type, initialBid);
    	this.modelo = modelo;
    	this.documento = documento;
    	this.combustivel = combustivel;
    	this.tipoPneu = tipoPneu;
    }
}
