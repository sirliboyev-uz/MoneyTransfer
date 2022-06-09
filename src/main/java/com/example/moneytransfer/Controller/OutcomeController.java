package com.example.moneytransfer.Controller;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.DTO.OutcomeDTO;
import com.example.moneytransfer.Service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {
    @Autowired
    OutcomeService outcomeService;
    @PostMapping
    public HttpEntity<?> outcome(@RequestBody OutcomeDTO outcomeDTO){
        ApiResponse apiResponse = outcomeService.getOutcome(outcomeDTO);
        return ResponseEntity.status(apiResponse.getType()?200:405).body(apiResponse.getMessage());
    }
}
