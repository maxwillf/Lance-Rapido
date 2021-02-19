package com.leilao.lance.rapido.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bid {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Integer id;

   @ManyToOne
   Product product;
   @ManyToOne
   User user;


   Double bidValue;
}
