package com.example.moneytransfer.Controller;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.Model.Card;
import com.example.moneytransfer.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/register")
    public HttpEntity<?> registerCard(@RequestBody Card card){
        ApiResponse apiResponse = cardService.register(card);
        return ResponseEntity.status(apiResponse.getType()?201:409).body(apiResponse.getMessage());
    }
    @PutMapping("/update/{id}")
    public HttpEntity<?> updateCard(@RequestBody Card card, @PathVariable Integer id){
        ApiResponse apiResponse = cardService.update(card,id);
        return ResponseEntity.status(apiResponse.getType()?200:404).body(apiResponse.getMessage());
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteCard(@PathVariable Integer id){
        ApiResponse apiResponse = cardService.delete(id);
        return ResponseEntity.status(apiResponse.getType()?200:404).body(apiResponse.getMessage());
    }
}
