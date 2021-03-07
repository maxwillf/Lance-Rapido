package com.leilao.lance.rapido.model;


import lombok.Data;

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
   Product product;
   @ManyToOne
   User user;

   Double bidValue;
}
