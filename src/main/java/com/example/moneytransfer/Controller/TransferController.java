package com.example.moneytransfer.Controller;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.DTO.Transfer;
import com.example.moneytransfer.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping
    public HttpEntity<?> transfer(@RequestBody Transfer transfer){
        ApiResponse apiResponse = transferService.moneyTransfer(transfer);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
}
