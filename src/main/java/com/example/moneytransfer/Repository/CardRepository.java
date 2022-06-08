package com.example.moneytransfer.Repository;

import com.example.moneytransfer.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByCardNumber(Long cardNumber);
    Optional<Card> findByCardNumberAndCardPassword(Long cardNumber, Integer cardPassword);
}
