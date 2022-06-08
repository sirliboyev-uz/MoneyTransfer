package com.example.moneytransfer.Service;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.DTO.Transfer;
import com.example.moneytransfer.Model.Card;
import com.example.moneytransfer.Model.Income;
import com.example.moneytransfer.Model.Outcome;
import com.example.moneytransfer.Repository.CardRepository;
import com.example.moneytransfer.Repository.IncomeRepository;
import com.example.moneytransfer.Repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;
    public ApiResponse moneyTransfer(Transfer transfer){
        Optional<Card> fromCard = cardRepository.findByCardNumberAndCardPassword(transfer.getFromCard(), transfer.getCardPassword());
        if (fromCard.isPresent()){
            Optional<Card> toCardNumber = cardRepository.findByCardNumber(transfer.getToCard());
            if (toCardNumber.isPresent()){
                Card card = fromCard.get();
                Long balance = fromCard.get().getBalance();
                Card card1 = toCardNumber.get();
                if (balance >= transfer.getAmount()){
                    Outcome outcome=new Outcome();
                    outcome.setFromCard(transfer.getFromCard());
                    outcome.setToCard(transfer.getToCard());
                    outcome.setAmount(transfer.getAmount()+(transfer.getAmount()/100));
                    outcome.setCommissionAmount((double)(transfer.getAmount()/100));

                    Income income=new Income();
                    income.setFromCard(transfer.getFromCard());
                    income.setAmount(transfer.getAmount());
                    income.setToCard(transfer.getToCard());

                    card.setBalance(card.getBalance() - transfer.getAmount()-transfer.getAmount()/100);
                    card1.setBalance(card1.getBalance() + transfer.getAmount());

                    incomeRepository.save(income);
                    outcomeRepository.save(outcome);
                    cardRepository.save(card1);
                    cardRepository.save(card);
                    return new ApiResponse("Successfully transfer", true);
                }
                return new ApiResponse("No money", false);
            }
            return new ApiResponse("Error card1", false);
        }
        return new ApiResponse("Wrong card or password", false);
    }
}
