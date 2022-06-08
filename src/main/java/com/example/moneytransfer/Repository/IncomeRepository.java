package com.example.moneytransfer.Repository;

import com.example.moneytransfer.Model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
}
