package com.example.moneytransfer.Repository;

import com.example.moneytransfer.Model.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
    Optional<Outcome> findAllByFromCard(Long fromCard);
}
