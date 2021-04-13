package com.leilao.lance.rapido.model;


import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Movel extends Product{
	
	private static final long serialVersionUID = 1L;
	
	private Boolean dobravel;
	private String materiais;
	private int altura;
	private int largura;
	private int comprimento;

	public Movel(){
		super();
	}
	public Movel(User user, String type, double initialBid, Boolean dobravel, String materiais, int altura, int largura, int comprimento) {
    	super(user, type, initialBid);
    	this.dobravel = dobravel;
    	this.materiais = materiais;
    	this.altura = altura;
    	this.largura = largura;
    	this.comprimento = comprimento;
    }
}
