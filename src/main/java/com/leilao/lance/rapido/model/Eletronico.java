package com.leilao.lance.rapido.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Eletronico extends Product{
	
	private static final long serialVersionUID = 1L;
	
	private int anoCompra;
	private int voltagem;
	private String tipoTomada;
	public Eletronico(){
		super(
		);
	};
    public Eletronico(User user, String type, double initialBid, int anoCompra, int voltagem, String tipoTomada) {
    	super(user, type, initialBid);
    	this.anoCompra = anoCompra;
    	this.voltagem = voltagem;
    	this.tipoTomada = tipoTomada;
    }

}
