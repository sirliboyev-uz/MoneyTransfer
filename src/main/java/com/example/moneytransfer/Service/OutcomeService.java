package com.example.moneytransfer.Service;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.DTO.OutcomeDTO;
import com.example.moneytransfer.Model.Card;
import com.example.moneytransfer.Model.Outcome;
import com.example.moneytransfer.Repository.CardRepository;
import com.example.moneytransfer.Repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutcomeService {
    @Autowired
    OutcomeRepository outcomeRepository;
    @Autowired
    CardRepository cardRepository;

    public ApiResponse getOutcome(OutcomeDTO outcomeDTOt){
        Optional<Card> optionalCard = cardRepository.findByCardNumberAndCardPassword(outcomeDTOt.getCardNumber(), outcomeDTOt.getCardPassword());
        if (optionalCard.isPresent()){
            Optional<Outcome> optionalOutcome = outcomeRepository.findAllByFromCard(outcomeDTOt.getCardNumber());
            if (optionalOutcome.isPresent()){
                Long outcome = optionalOutcome.get().getAmount();
                return new ApiResponse(outcome.toString(),true);
            }
            return new ApiResponse("Not outcome", false);
        }
        return new ApiResponse("Card or password wrong",false);
    }
}