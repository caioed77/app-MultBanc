package com.multBancapp.apimultbanc.controllers;


import com.multBancapp.apimultbanc.models.dto.TransferDTO;
import com.multBancapp.apimultbanc.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/tranferencia")
public class TranferController {

    private final TransferService transferService;

    public TranferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity transferAccount(@RequestBody TransferDTO transfer) {
        transferService.generatedTransfer(transfer);
        return ResponseEntity.ok().build();
    }


}
