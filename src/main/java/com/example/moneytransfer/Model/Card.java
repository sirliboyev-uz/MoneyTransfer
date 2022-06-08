package com.example.moneytransfer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private Long cardNumber;

    @Column(nullable = false)
    private Integer cardPassword;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private String expiredDate;
}
