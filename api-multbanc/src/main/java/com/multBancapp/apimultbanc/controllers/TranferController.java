package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/tranferencia")
public class TranferController {

    private final TransferService transferService;

    public TranferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity transferAccount(
            @RequestParam Integer numeroContaOrigem,
            @RequestParam Integer numeroContaDestino,
            @RequestParam BigDecimal valorTransferencia) {
        transferService.generatedTransfer(numeroContaOrigem, numeroContaDestino, valorTransferencia);
        return ResponseEntity.ok().build();
    }


}
