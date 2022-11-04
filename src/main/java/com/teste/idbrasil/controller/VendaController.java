package com.teste.idbrasil.controller;

import com.teste.idbrasil.dto.PaymentDTO;
import com.teste.idbrasil.dto.ProductDTO;
import com.teste.idbrasil.dto.VendaDTO;
import com.teste.idbrasil.service.VendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping(path = "/v1/venda", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendaController {

    @Autowired
    VendaService vendaService;

    @PostMapping(path = "/vender")
    public ResponseEntity vender(@RequestBody @NotNull @Valid VendaDTO dto) {
        try {
            return ResponseEntity.ok(vendaService.calcularVender(dto.getProduct(), dto.getPayment()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
