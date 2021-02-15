package com.leilao.lance.rapido.model;


import lombok.Data;

import java.io.Serializable;
@Data
public class BankInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bankName;
    private String bankCode;
    private String account;
    private String agency;
}
