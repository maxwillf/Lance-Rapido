package com.leilao.lance.rapido.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bid {
   @ManyToOne
   Product product;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Integer id;

   Double bidValue;
}
