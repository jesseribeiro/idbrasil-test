package com.teste.idbrasil.controller;

import com.teste.idbrasil.bean.ProductBean;
import com.teste.idbrasil.dto.PaymentDTO;
import com.teste.idbrasil.dto.ProductDTO;
import com.teste.idbrasil.service.ProductService;
import com.teste.idbrasil.service.VendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAll() {
        List<ProductBean> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}
