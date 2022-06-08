package com.example.moneytransfer.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transfer {
    private Long fromCard;
    private Integer cardPassword;
    private Long toCard;
    private Long amount;
}
