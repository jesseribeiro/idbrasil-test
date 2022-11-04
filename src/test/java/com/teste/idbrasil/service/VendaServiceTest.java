package com.teste.idbrasil.service;

import com.teste.idbrasil.dto.PaymentDTO;
import com.teste.idbrasil.dto.ProductDTO;
import com.teste.idbrasil.exception.DadosInvalidosException;
import com.teste.idbrasil.exception.NumeroParcelasException;
import com.teste.idbrasil.exception.SemValorException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class VendaServiceTest {

    @Autowired
    private VendaService vendaService;

    @Test
    public void venda() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Teste");
        productDTO.setPrice(new BigDecimal(2500));

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setEntry(new BigDecimal(500));
        paymentDTO.setInstallments(4);

        vendaService.calcularVender(productDTO, paymentDTO);
        Assert.assertTrue(true);
    }

    @Test(expected = NumeroParcelasException.class)
    public void vendaSemNumeroParcelas() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Teste2");
        productDTO.setPrice(new BigDecimal(2500));

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setEntry(new BigDecimal(500));

        vendaService.calcularVender(productDTO, paymentDTO);
    }

    @Test
    public void getVendaSemId() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(new BigDecimal(2500));

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setEntry(new BigDecimal(500));
        paymentDTO.setInstallments(10);

        Assert.assertTrue(true);
    }

    @Test(expected = SemValorException.class)
    public void getVendaSemValor() {
        ProductDTO productDTO = new ProductDTO();

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setEntry(new BigDecimal(500));
        paymentDTO.setInstallments(10);

        vendaService.calcularVender(productDTO, paymentDTO);
    }

    @Test(expected = DadosInvalidosException.class)
    public void getProduct() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setInstallments(3);
        paymentDTO.setEntry(new BigDecimal(50));
        vendaService.calcularVender(null, paymentDTO);
    }
}
