package com.example.moneytransfer.Service;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.Model.Card;
import com.example.moneytransfer.Model.Users;
import com.example.moneytransfer.Repository.CardRepository;
import com.example.moneytransfer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CardService implements UserDetailsService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;
    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;
    public ApiResponse register(Card card){
        Optional<Card> cardOptional = cardRepository.findByCardNumber(card.getCardNumber());
        if (!cardOptional.isPresent()){
            Optional<Users> byUsername = userRepository.findByUsername(card.getUsername());
            if (byUsername.isPresent()){
                Card card1 = new Card();
                card1.setUsername(card.getUsername());
                card1.setCardNumber(card.getCardNumber());
                card1.setBalance(card.getBalance());
                card1.setCardPassword(card.getCardPassword());
                card1.setExpiredDate(card.getExpiredDate());
                cardRepository.save(card1);
                return new ApiResponse("Successfully registered", true);
            }
            return new ApiResponse("User not found", false);
        }
        return new ApiResponse("Card already registered",false);
    }

    public ApiResponse update(Card card, Integer id){
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()){
            Card card1 = cardOptional.get();
            card1.setUsername(card.getUsername());
            card1.setBalance(card.getBalance());
            card1.setCardNumber(card.getCardNumber());
            card1.setCardPassword(card.getCardPassword());
            card1.setExpiredDate(card.getExpiredDate());
            cardRepository.save(card1);
            return new ApiResponse("Successfully updated",true);
        }
        return new ApiResponse("id not found", false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()){
            cardRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("id not found", false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+" not found username"));
    }
}
