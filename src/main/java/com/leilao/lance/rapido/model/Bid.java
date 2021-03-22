package com.leilao.lance.rapido.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Bid {

   public Bid(){}
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Integer id;

   @ManyToOne
   @JoinColumn(name = "product_id")
   @EqualsAndHashCode.Exclude
   @ToString.Exclude
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   Product product;
   @ManyToOne
   User user;
   Double bidValue;
   
   public Bid(Product product, User user, double bidValue) {
	   this.product = product;
	   this.user = user;
	   this.bidValue = bidValue;
   }
}
